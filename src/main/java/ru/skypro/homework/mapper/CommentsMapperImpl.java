package ru.skypro.homework.mapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Service
public class CommentsMapperImpl implements CommentsMapper {

//    @Mapping(target = "author", source = "author", qualifiedByName = "authorToInteger")
//    @Mapping(target = "authorFirstName", source = "author", qualifiedByName = "authorFirstNameFromAuthor")
////    @Mapping(target = "authorImage", source = "author", qualifiedByName = "authorImageToString")
////    @Mapping(target = "authorImage", source = "author")
//    CommentsDto toCommentsDto(Comment comment);
//
//    @Mapping(target = "createdAt", qualifiedByName = "instantToInteger")
//    @Named("instantToInteger")
//    default long instantToInteger(Instant instant) {
//        return instant.atZone(ZoneOffset.ofHours(3)).toInstant().toEpochMilli();
//    }
//    Comment toCommentEntityFromCreateOrUpdateComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);
//
////    @Named("authorImageToString")
////    default String authorImageToString(User user) {
////        if (user.getImage() == null) {
////            return null;
////        }
////        return "/users/image/" + user.getImage().getId();
////    }
//
//    @Named("authorToInteger")
//    default Integer authorToInteger(User user) {
//        return user.getId();
//    }
//
//    @Named("authorFirstNameFromAuthor")
//    default String authorFirstNameFromAuthor(User author) {
//        return author.getFirstName();
//    }
//
//    List<CommentsDto> toCommentsDto(List<Comment> comments);
//    Comment toCreateComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);
//
//    _____________________________________________________________________
@Override
    public CommentsDto toCommentsDto(Comment entity) {

    CommentsDto dto = new CommentsDto();
    dto.setAuthor(entity.getAuthor().getId());
    dto.setAuthorFirstName(entity.getAuthor().getFirstName());
    dto.setPk(entity.getId());
    dto.setText(entity.getText());

    if (entity.getAuthor().getImage() != null) {
        dto.setAuthorImage(String.format("/ads/image/%s", entity.getAuthor().getImage()));
    } else {
        dto.setAuthorImage(null);
    }
    dto.setCreatedAt(LocalDateTime.ofInstant(entity.getCreatedAt(), TimeZone.getDefault().toZoneId()));

    return dto;
}
@Override
    public Comment toCommentsEntity(CreateOrUpdateCommentDto dto, User author, Ad ad) {

        Comment entity = new Comment();
        entity.setText(dto.getText());
        entity.setAd(ad);
        entity.setAuthor(author);
        entity.setCreatedAt(Instant.now());

        return entity;
    }
}

