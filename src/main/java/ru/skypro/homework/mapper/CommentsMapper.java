package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

public interface CommentsMapper {

    CommentsDto toCommentsDto(Comment entity);

    Comment toCommentsEntity(CreateOrUpdateCommentDto dto, User author, Ad ad);

}
