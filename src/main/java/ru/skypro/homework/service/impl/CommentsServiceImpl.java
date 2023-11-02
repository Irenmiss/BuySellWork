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
import ru.skypro.homework.service.CommentsService;
import ru.skypro.homework.service.ValidationService;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private ValidationService validationService;
    private CommentsRepository commentsRepository;
    private AdsRepository adsRepository;
    private CommentsMapper commentsMapper;
    private UsersRepository usersRepository;

    @Override
    public CommentsDto createComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                     String userDetails) {

        if (!validationService.validate(createOrUpdateCommentDto)) {
            throw new ValidationException(createOrUpdateCommentDto.toString());
        }
        User user = usersRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Ad ad = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Advertisement not found"));
        Comment comment= commentsMapper.toCommentsEntity(createOrUpdateCommentDto, user, ad);
        commentsRepository.save(comment);
        return commentsMapper.toCommentsDto(comment);
    }
    @Override
    public CommentsDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto dto, String userDetails) {

        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Comment comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException("Advertisement not found"));

        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {

            comment.setText(dto.getText());
            commentsRepository.save(comment);

            CommentsDto commentDto = commentsMapper.toCommentsDto(comment);

            return commentDto;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
    @Override
    @Transactional
    public boolean deleteComment(Integer adId, Integer commentId, String userDetails) {

        User authorOrAdmin = usersRepository.findByUsername(userDetails);
        Comment comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException(""));

        if (comment.getAuthor().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (Role.ADMIN)) {

            commentsRepository.delete(comment);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public GetAllCommentsDto getComments(Integer id) {
        List<Comment> commentsEntity = commentsRepository.findByAdPk(id);
        List<CommentsDto> dto = new ArrayList<>();
        for (Comment comment : commentsEntity) {
            dto.add(commentsMapper.toCommentsDto(comment));
        }
        return new GetAllCommentsDto(dto.size(), dto);
    }

}


