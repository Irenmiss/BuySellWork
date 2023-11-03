package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.entity.Ad;
import ru.skypro.homework.model.entity.Comment;
import ru.skypro.homework.model.entity.User;

/**
 * Настройка маппинга для преобразования сущностей комментариев в DTO и обратно
 */
public interface CommentsMapper {

    CommentsDto toCommentsDto(Comment entity);

    Comment toCommentsEntity(CreateOrUpdateCommentDto dto, User author, Ad ad);

}
