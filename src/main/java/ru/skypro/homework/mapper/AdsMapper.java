package ru.skypro.homework.mapper;
import org.mapstruct.*;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdsMapper {

    //сущность Ad в AdsDto
    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInt")
//    @Mapping(target = "pk", source = "pk")
    AdsDto toAdsDto(Ad ad);
    List<AdsDto> toAdsDto(List<Ad> ads);
    @Named("authorToInt")
    default Integer authorToInt(User user) {
        return user.getId();
    }
    @Mapping(target = "pk", source = "pk")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.username")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "price", ignore = true)
    GetFullAdInfoDto toGetFullAddInfoDto(Ad ad);

    //DTO в сущность Ad
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    Ad toAd(CreateOrUpdateAdDto dto);

}


