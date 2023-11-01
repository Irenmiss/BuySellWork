package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

public interface AdsMapper {

    AdsDto toAdsDto(Ad entity);

    Ad toAdEntity(CreateOrUpdateAdDto createOrUpdateAdDto, User author);

    GetFullAdInfoDto toGetFullAdInfoDto(Ad entity);
}
