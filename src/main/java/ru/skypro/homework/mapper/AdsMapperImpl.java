package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.model.entity.Ad;
import ru.skypro.homework.model.entity.User;

/**
 * Настройка маппинга для преобразования сущностей объявлений в DTO и обратно
 */

@Service
public class AdsMapperImpl implements AdsMapper {
    @Override
    public AdsDto toAdsDto(Ad entity) {

        AdsDto dto = new AdsDto();

        dto.setAuthor(entity.getAuthor().getId());
        dto.setPk(entity.getPk());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        if (entity.getImage() != null) {
            dto.setImage(String.format("/ads/image/%s", entity.getImage()));
        } else {
            dto.setImage(null);
        }

        return dto;
    }

    @Override
    public Ad toAdEntity(CreateOrUpdateAdDto dto, User author) {
        Ad entity = new Ad();
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setAuthor(author);

        return entity;
    }

    @Override
    public GetFullAdInfoDto toGetFullAdInfoDto(Ad entity) {

        GetFullAdInfoDto dto = new GetFullAdInfoDto();
        dto.setPk(entity.getPk());
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setAuthorLastName(entity.getAuthor().getLastName());
        dto.setDescription(entity.getDescription());
        dto.setEmail(entity.getAuthor().getUsername());
        dto.setPhone(entity.getAuthor().getPhone());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        if (entity.getImage() != null) {
            dto.setImage(String.format("/ads/image/%s", entity.getImage()));
        } else {
            dto.setImage(null);
        }
        return dto;
    }
}


