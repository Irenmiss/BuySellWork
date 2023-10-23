package ru.skypro.homework.mapper;
import org.mapstruct.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdsMapper {

    //сущность в DTO
    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInt")
    AdsDto toAdsDto(Ad ad);
    List<AdsDto> toAdsDto(List<Ad> ads);
    @Named("authorToInt")
    default Integer authorToInt(User author) {
        return author.getId();
    }

    //DTO в сущность
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "author", ignore = true)
    Ad toAd(CreateOrUpdateAdDto dto);

    @Mapping(target = "pk", source = "pk")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "description", ignore = true)
    GetFullAdInfoDto toGetFullAddInfoDto(Ad ad);

}
