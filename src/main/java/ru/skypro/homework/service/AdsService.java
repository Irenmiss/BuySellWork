package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;

import java.io.IOException;
import java.util.List;

public interface AdsService {

    List<AdsDto> getAllAds();

    AdsDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, String userDetails) throws IOException;

    AdsDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String userDetails);

    boolean deleteById(Integer id, String userDetails);

    GetFullAdInfoDto getFullAdInfoDto(Integer id);

    void updateImage(Integer id, MultipartFile image) throws IOException;

    List<AdsDto> getAllMyAds(String userDetails);

    Ad findAdById(Integer id);
}
