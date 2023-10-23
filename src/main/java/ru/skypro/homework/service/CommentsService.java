package ru.skypro.homework.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllCommentsDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;

public interface CommentsService {
//    List <CommentsDto> getAdComments (Integer id); // Получение комментариев объявления
//
//    // Добавление комментария к объявлению
//    Comment addCommentToAd (Integer id , CreateOrUpdateCommentDto createOrUpdateCommentDto , Authentication authentication);
//
//    void deleteComment (Integer adId , Integer commentId); // Удаление комментария
//
//    // Обновление комментария
//    Comment updateComment (Integer adId , Integer commentId , CreateOrUpdateCommentDto createOrUpdateCommentDto);
    GetAllCommentsDto getComments(int id);
    CommentsDto addComment(int id, CreateOrUpdateCommentDto text);

}
