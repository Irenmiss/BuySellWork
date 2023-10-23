package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;

import java.util.List;

public interface AdsService {
    //просмотреть объявление по номеру
    GetFullAdInfoDto getAdInfo (Integer id);
    //создать объявление
    AdsDto saveAd(CreateOrUpdateAdDto ad, String email);
    //обновить объявление по номеру
    AdsDto updateAd(int id, CreateOrUpdateAdDto createOrUpdateAdsDto);
    //удалить объявление по номеру
    void deleteAdById(Integer id);
    //просмотреть все объявления
    List<AdsDto> getAllAds();
}
