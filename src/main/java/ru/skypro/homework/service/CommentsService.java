package ru.skypro.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllCommentsDto;

/**
 * Сервис для работы с комментариями
 */
public interface CommentsService {
    /**
     * Создание комментария
     * {@link ru.skypro.homework.service.impl.CommentsServiceImpl#createComment(Integer, CreateOrUpdateCommentDto, String)}
     *
     * @param id          идентификатор комментария
     * @param dto         данные о комментарии в виде DTO
     * @param userDetails информация о пользователе
     * @return созданный комментарий
     */
    CommentsDto createComment(Integer id, CreateOrUpdateCommentDto dto, String userDetails);

    /**
     * Редактирование комментария
     * {@link ru.skypro.homework.service.impl.CommentsServiceImpl#updateComment(Integer, Integer, CreateOrUpdateCommentDto, String)}
     *
     * @param adId        идентификатор объявления, для которого указан данный комментарий
     * @param commentId   идентификатор редактируемого комментария
     * @param dto         тело измененного комментария в виде DTO
     * @param userDetails информация о пользователе
     * @return обновленный комментарий
     */

    CommentsDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto dto, String userDetails);

    /**
     * Удаление комментарий
     * *{@link ru.skypro.homework.service.impl.CommentsServiceImpl#deleteComment(Integer, Integer, String)}
     *
     * @param adId        идентификатор объявления, для которого указан удаляемый комментарий
     * @param commentId   идентификатор удаляемого комментария
     * @param userDetails информация о пользователе
     * @return true or false
     */

    @Transactional
    boolean deleteComment(Integer adId, Integer commentId, String userDetails);

    /**
     * Просмотр комментария
     * {@link ru.skypro.homework.service.impl.CommentsServiceImpl#getComments(Integer)}
     *
     * @param id идентификатор комментария
     * @return комментарий
     */

    GetAllCommentsDto getComments(Integer id);

}