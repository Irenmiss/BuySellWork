package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ValidationService;

import javax.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Data
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {
    private AdsRepository adsRepository;
    private AdsMapper adsMapper;
    private ImageService imageService;
    private UsersRepository usersRepository;
    private ValidationService validationService;
    CommentsRepository commentsRepository;

    @Override
    public AdsDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto,
                           MultipartFile image,
                           String userDetails) {
        if (!validationService.validate(createOrUpdateAdDto)) {
            throw new ValidationException(createOrUpdateAdDto.toString());
        }

        User user = usersRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Ad ad = adsMapper.toAdEntity(createOrUpdateAdDto, user);
        ad.setImage(imageService.addImage(image));
        ad.setAuthor(user);
        adsRepository.save(ad);
        return adsMapper.toAdsDto(ad);
    }

    @Override
    @Transactional
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
    public GetAllAdsDto getAllAds() {

        List<AdsDto> adsDto = adsRepository.findAll().stream()
                .map(adsMapper::toAdsDto)
                .collect(Collectors.toList());
        return new GetAllAdsDto(adsDto.size(), adsDto);
    }

    @Override
    public boolean updateImage(Integer id, MultipartFile image) {
        Ad ad = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Advertisement not found"));
        String imageId = imageService.addImage(image);
        ad.setImage(imageId);
        adsRepository.save(ad);
        return true;
    }

    @Override
    public GetAllAdsDto getAllMyAds(String userDetails) {
        User author = usersRepository.findByUsername(userDetails);
        if (author != null) {
            List<Ad> adEntity = adsRepository.findByAuthor(author);
            List<AdsDto> dto = new ArrayList<>();
            for (Ad ad : adEntity) {
                dto.add(adsMapper.toAdsDto(ad));
            }
            return new GetAllAdsDto(dto.size(), dto);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}

