package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.model.entity.Ad;
import ru.skypro.homework.model.entity.User;

/**
 * Настройка маппинга для преобразования сущностей объявлений в DTO и обратно
 */

public interface AdsMapper {

    AdsDto toAdsDto(Ad entity);

    Ad toAdEntity(CreateOrUpdateAdDto createOrUpdateAdDto, User author);

    GetFullAdInfoDto toGetFullAdInfoDto(Ad entity);
}
