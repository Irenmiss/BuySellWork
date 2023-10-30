package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.Enums.Role;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.exceptions.ValidationException;
import ru.skypro.homework.mapper.CommentsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentsService;
import ru.skypro.homework.service.ValidationService;

import java.time.Instant;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private CommentsRepository commentsRepository;
    private CommentsMapper commentsMapper;
    private AdsRepository adsRepository;
    private UsersRepository usersRepository;
    private AdsService adsService;
    private ValidationService validationService;


    @Override
    public CommentsDto createAdComment(Integer id,
                                       CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                       String userDetails) {
        if (!validationService.validate(createOrUpdateCommentDto)) {
            throw new ValidationException(createOrUpdateCommentDto.toString());
        }
        User user = usersRepository.findByUsername(userDetails);
        if (user != null) {
            Ad ad = adsRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Advertisement not found"));
            Comment comment = commentsMapper.toCommentEntityFromCreateOrUpdateComment(createOrUpdateCommentDto);
            comment.setAd(ad);
            comment.setAuthor(user);
            commentsRepository.save(comment);
            return commentsMapper.toCommentsDto(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public CommentsDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                     String userDetails) {
        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() -> new NotFoundEntityException("Comment not found"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
            comment.setText(createOrUpdateCommentDto.getText());
            comment.setCreatedAt(Instant.now().toEpochMilli());
            commentsRepository.save(comment);
            return commentsMapper.toCommentsDto(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public void deleteCommentById(Integer adId, Integer commentId, String userDetails) {
        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() -> new NotFoundEntityException("Comment not found"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
            commentsRepository.deleteById(commentId);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public List<CommentsDto> getCommentByIdAd(Integer id) {
        return commentsMapper.toCommentsDto(commentsRepository.findByAdPk(id));
    }

}






