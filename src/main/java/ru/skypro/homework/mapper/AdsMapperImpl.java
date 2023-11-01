package ru.skypro.homework.mapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Service
public class AdsMapperImpl implements AdsMapper {
//    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
//    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInt")
//    @Mapping(target = "pk", source = "pk")
//    @Mapping(target = "image", source = "image")
//    AdsDto toAdsDto(Ad ad);
//
//    List<AdsDto> toAdsDto(List<Ad> ads);
//
//    @Mapping(target = "author", ignore = true)
//    @Mapping(target = "image", ignore = true)
//    @Mapping(target = "pk", ignore = true)
//    Ad toAdEntity(CreateOrUpdateAdDto createOrUpdateAdDto, User author);
//
//    @Mapping(target = "authorFirstName", source = "author.firstName")
//    @Mapping(target = "authorLastName", source = "author.lastName")
//    @Mapping(target = "email", source = "author.username")
//    @Mapping(target = "phone", source = "author.phone")
//    @Mapping(target = "image", source = "image")
////    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
//    GetFullAdInfoDto toGetFullAdInfoDto(Ad ad);
//
////    @Named("imageToPathString")
////    default String imageToPathString(Image image) {
////        return "/ads/image/" + image.getId();
////    }
//
//    @Named("authorToInt")
//    default Integer authorToInt(User user) {
//        return user.getId();
//    }
//    ___________________________________________________________________
@Override
    public AdsDto toAdsDto(Ad entity) {

    AdsDto dto = new AdsDto();

    dto.setAuthor(entity.getAuthor().getId());
    dto.setImage(entity.getImage());
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


