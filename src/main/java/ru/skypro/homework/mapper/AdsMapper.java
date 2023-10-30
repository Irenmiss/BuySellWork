package ru.skypro.homework.mapper;
import org.mapstruct.*;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdsMapper {
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInt")
    AdsDto toAdsDto(Ad ad);

    List<AdsDto> toAdsDto(List<Ad> ads);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "pk", ignore = true)
    Ad toAdEntity(CreateOrUpdateAdDto createOrUpdateAdDto);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.username")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
    GetFullAdInfoDto toGetFullAdInfoDto(Ad ad);

    @Named("imageToPathString")
    default String imageToPathString(Image image) {
        return "/ads/image/" + image.getId();
    }

    @Named("authorToInt")
    default Integer authorToInt(User user) {
        return user.getId();
    }
}


