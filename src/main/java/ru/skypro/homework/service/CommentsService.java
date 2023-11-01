package ru.skypro.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllCommentsDto;

import java.util.List;

public interface CommentsService {
    CommentsDto createAdComment(Integer id,
                                CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                String userDetails);

    CommentsDto updateComment(Integer adId, Integer commentId, CommentsDto commentsDto,
                              String userDetails);

    @Transactional
    boolean deleteCommentById(Integer adId, Integer commentId, String userDetails);

    GetAllCommentsDto getCommentByIdAd(Integer id);

}
