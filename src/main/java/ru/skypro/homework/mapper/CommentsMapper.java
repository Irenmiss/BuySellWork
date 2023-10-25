package ru.skypro.homework.mapper;
import org.mapstruct.*;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentsMapper {
    @Mapping(target = "createdAt", qualifiedByName = "instantToInteger")
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Named("instantToInteger")
    default long instantToInteger(Instant instant) {
        return instant.atZone(ZoneOffset.ofHours(3)).toInstant().toEpochMilli();
    }
    Comment toCreateComment(CreateOrUpdateCommentDto createOrUpdateCommentDto);
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "createdAt", qualifiedByName = "instantToInteger")
    CommentsDto toCommentsDto(Comment comment);

    List<CommentsDto> toCommentsDto(List<Comment> comments);
}
