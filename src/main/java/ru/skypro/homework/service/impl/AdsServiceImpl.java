package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.Enums.Role;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetAllAdsDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ValidationService;

import javax.validation.ValidationException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private AdsRepository adsRepository;
    private AdsMapper adsMapper;
    private ImageService imageService;
    private UsersRepository usersRepository;
    private ImageRepository imageRepository;
    private CommentsRepository commentsRepository;
    private ValidationService validationService;

    @Override
    public AdsDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto,
                           MultipartFile image,
                           String userDetails) throws IOException {
        if (!validationService.validate(createOrUpdateAdDto)) {
            throw new ValidationException(createOrUpdateAdDto.toString());
        }

        User user = usersRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Ad ad = adsMapper.toAdEntity(createOrUpdateAdDto);
        ad.setImage(imageService.upload(image));
        ad.setAuthor(user);
        adsRepository.save(ad);
        return adsMapper.toAdsDto(ad);
    }

    @Override
    public AdsDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String userDetails) {
        if (!validationService.validate(createOrUpdateAdDto)) {
            throw new ValidationException(createOrUpdateAdDto.toString());
        }
        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Ad ad = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Advertisement not found"));
        if (ad.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
            ad.setDescription(createOrUpdateAdDto.getDescription());
            ad.setTitle(createOrUpdateAdDto.getTitle());
            ad.setPrice(createOrUpdateAdDto.getPrice());
            adsRepository.save(ad);
            return adsMapper.toAdsDto(ad);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public boolean deleteById(Integer id, String userDetails) {
        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Ad ad = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Advertisement not found"));
        if (ad.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == Role.ADMIN) {
            adsRepository.deleteById(id);
            commentsRepository.deleteAllByAdPk(ad.getPk());
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public GetFullAdInfoDto getFullAdInfoDto(Integer id) {
        Ad entity = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Advertisement not found"));
        GetFullAdInfoDto dto = adsMapper.toGetFullAdInfoDto(entity);
        return dto;
    }

    @Override
    public List<AdsDto> getAllAds() {
        return adsMapper.toAdsDto(adsRepository.findAll());
    }

    @Override
    public void updateImage(Integer id, MultipartFile image) throws IOException {
        Ad ad = findAdById(id);
        imageRepository.delete(ad.getImage());
        ad.setImage(imageService.upload(image));
        adsRepository.save(ad);
    }

    @Override
    public List<AdsDto> getAllMyAds(String userDetails) {
        User author = usersRepository.findByUsername(userDetails);
        if (author != null) {
            List<Ad> adEntity = adsRepository.findByAuthor(author);
            List<AdsDto> dto = new ArrayList<>();
            for (Ad ad : adEntity) {
                dto.add(adsMapper.toAdsDto(ad));
            }
            return (List<AdsDto>) new GetAllAdsDto(dto.size(), dto);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public Ad findAdById(Integer id) {
        Optional<Ad> ad = adsRepository.findById(id);
        return ad.get();
    }
}

