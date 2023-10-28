package ru.skypro.homework.service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;

public interface CommentsService {

    List<CommentsDto> getAdComments(Integer id);

    public CommentsDto createAdComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                       org.springframework.security.core.Authentication authentication);


    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto);

    void deleteCommentById(Integer commentId);
}
