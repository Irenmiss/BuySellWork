package ru.skypro.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllAdsDto;
import ru.skypro.homework.dto.GetAllCommentsDto;

import java.util.List;

public interface CommentsService {

    CommentsDto createComment(Integer id, CreateOrUpdateCommentDto dto, String userDetails);

    CommentsDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto dto, String userDetails);

    @Transactional
    boolean deleteComment(Integer adId, Integer commentId, String userDetails);

    GetAllCommentsDto getComments(Integer id);

}