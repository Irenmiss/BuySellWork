package ru.skypro.homework.mapper;
import org.mapstruct.*;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllCommentsDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentsMapper {
//    List <CommentsDto> toCommentsDto(List<Comment> comments);
////    @Mapping(target = "pk", source = "user_id")
//    @Mapping(target = "author", ignore = true)
//    @Mapping (target = "createdAt", ignore = true)
////    @Mapping (target = "ad", ignore = true)
//    Comment toCommenty (CommentsDto CommentsDto); // конвертация DTO в сущность
//
////    @Mapping(source = "pk", target = "user_id")
//    @Mapping(target = "author", source = "author.id")
//    @Mapping(target = "createdAt", qualifiedByName = "instantToInteger")
//   CommentsDto toCommentDto (Comment comment); // конвертация сущности в DTO
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "author", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
////    @Mapping (target = "ad", ignore = true)
//    Comment toCommentyCr (CreateOrUpdateCommentDto dto); // конвертация новых комментарий
//
//    @Named("instantToInteger")
//    default long instantToInteger(Instant instant) {
//        return instant.atZone(ZoneOffset.ofHours(3)).toInstant().toEpochMilli();
//    }
@Mapping(target = "createdAt", ignore = true)
//@Mapping(source = "pk", target = "id")
@Mapping(source = "author", target = "author.id")
Comment commentDtoToComment(CommentsDto commentsDto);


//    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToLong")
    CommentsDto commentToCommentDto(Comment comment);



    GetAllCommentsDto listCommentToCommentDto(int count, Collection<Comment> results);


    @Named("localDateTimeToLong")
    default Long localDateTimeToLong(LocalDateTime dateTime) {
        return dateTime.toInstant(ZonedDateTime.now().getOffset())
                .toEpochMilli();
    }

    @Named("longToLocalDateTime")
    default LocalDateTime longToLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
    }

}
