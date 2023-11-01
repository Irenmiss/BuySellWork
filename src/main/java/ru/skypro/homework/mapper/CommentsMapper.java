package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

public interface CommentsMapper {
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
    CommentsDto toCommentsDto(Comment entity);

    Comment toCommentsEntity(CreateOrUpdateCommentDto dto, User author, Ad ad);
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

}
