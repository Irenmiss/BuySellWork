package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.Enums.Role;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllCommentsDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
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
            Comment comment = commentsMapper.toCommentsEntity(createOrUpdateCommentDto, user, ad);
            comment.setAd(ad);
            comment.setAuthor(user);
            commentsRepository.save(comment);
            return commentsMapper.toCommentsDto(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public CommentsDto updateComment(Integer adId, Integer commentId, CommentsDto commentsDto,
                                     String userDetails) {
        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() -> new NotFoundEntityException("Comment not found"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
            comment.setText(commentsDto.getText());
            commentsRepository.save(comment);
            return commentsMapper.toCommentsDto(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    @Transactional
    public boolean deleteCommentById(Integer adId, Integer commentId, String userDetails) {
        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Comment comment = commentsRepository.findById(commentId).orElseThrow(() -> new NotFoundEntityException("Comment not found"));
        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {
            commentsRepository.deleteById(commentId);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public GetAllCommentsDto getCommentByIdAd(Integer id) {
        List<Comment> commentsEntity = commentsRepository.findByAdPk(id);
        List<CommentsDto> dto = new ArrayList<>();

        for (Comment comment : commentsEntity) {
            dto.add(commentsMapper.toCommentsDto(comment));
        }

        return new GetAllCommentsDto(dto.size(), dto);
    }
}

