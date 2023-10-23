package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;
@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final UsersRepository userRepository;
    private final AdsMapper adsMapper;

    @Override
    public GetFullAdInfoDto getAdInfo(Integer id) {
        Ad ad = adsRepository.findById(id).orElseThrow();
        return adsMapper.toGetFullAddInfoDto(ad);
    }

    @Override
    public AdsDto saveAd(CreateOrUpdateAdDto createOrUpdateAdDto, String email) {
        Integer id = adsRepository.save(adsMapper.toAd(createOrUpdateAdDto)).getPk();
        Ad savedAd = adsRepository.findById(id).orElseThrow();
        return adsMapper.toAdsDto(savedAd);
    }

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
        adsRepository.deleteById(id);

    }
    @Override
    public List<AdsDto> getAllAds() {
        return adsMapper.toAdsDto(adsRepository.findAll());
    }
}
