package ru.skypro.homework.service;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetAllAdsDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;

public interface AdsService {
    AdsDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto,
                    MultipartFile image,
                    String userDetails);

    @Transactional
    AdsDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String userDetails);

    boolean deleteById(Integer id, String userDetails);

    GetFullAdInfoDto getFullAdInfoDto(Integer id);

    GetAllAdsDto getAllAds();

    boolean updateImage(Integer id, MultipartFile image);

    GetAllAdsDto getAllMyAds(String userDetails);

}
