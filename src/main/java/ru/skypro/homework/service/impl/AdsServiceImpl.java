package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private AdsRepository adsRepository;
    private UsersRepository usersRepository;
    private AdsMapper adsMapper;
    private CommentsRepository commentsRepository;
    private ImageService imageService;

    @Override
    public GetFullAdInfoDto getAdInfo(Integer id) {
        Ad ad = adsRepository.findById(id).orElseThrow();
        return adsMapper.toGetFullAdInfoDto(ad);
    }

//    @Override
//    public AdsDto saveAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, String userDetails) {
//        User user = usersRepository.findByUsername(userDetails);
//        Ad entity = adsMapper.toAd(createOrUpdateAdDto);
//        String imageId = imageService.addImage(image);
//        entity.setImage(imageId);
//        entity.setAuthor(user);
//        adsRepository.save(entity);
//        return adsMapper.toAdsDto(entity);
//    }

    @Override
    public AdsDto updateAd(int id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        Ad ad = adsRepository.findById(id).orElseThrow();
        ad.setDescription (createOrUpdateAdDto.getDescription());;
        ad.setTitle(createOrUpdateAdDto.getTitle());
        ad.setPrice(createOrUpdateAdDto.getPrice());
        return adsMapper.toAdsDto(adsRepository.save(ad));
    }

    @Override
    public void deleteAdById(Integer id) {
        Ad ad = adsRepository.findById(id).orElseThrow();
        adsRepository.deleteById(id);
        commentsRepository.deleteAllByAdPk(ad.getPk());

    }
    @Override
    public List<AdsDto> getAllAds() {
        return adsMapper.toAdsDto(adsRepository.findAll());
    }


//    @Override
//    public boolean updateAdImage(Integer id, MultipartFile image) {
//        String imageId = imageService.addImage(image);
//        Ad ad = adsRepository.findById(id).orElseThrow();
//        ad.setImage(imageId);
//        adsRepository.save(ad);
//        return true;
//    }
}
