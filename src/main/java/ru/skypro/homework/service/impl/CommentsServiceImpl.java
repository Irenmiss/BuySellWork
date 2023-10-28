package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.CommentsService;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private CommentsRepository commentsRepository;
    private CommentsMapper commentsMapper;
    private AdsRepository adsRepository;
    private UsersRepository usersRepository;

    @Override
    public List<CommentsDto> getAdComments(Integer id) {
        return commentsMapper.toCommentsDto(commentsRepository.findByAdPk(id));
    }

    @Override
    public CommentsDto createAdComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                       Authentication authentication) {
        User user = usersRepository.findByUsername(authentication.getName());
        Ad ad = adsRepository.findByAuthorId(id);
        Comment comment = commentsMapper.toCreateComment(createOrUpdateCommentDto);

        comment.setAd(ad);
        comment.setAuthor(user);

        commentsRepository.save(comment);
        return commentsMapper.toCommentsDto(comment);
    }
    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        Comment comment = commentsRepository.findByAdPk(adId).get(commentId);
        comment.setText(createOrUpdateCommentDto.getText());
        return commentsRepository.save(comment);
    }
        @Override
        public void deleteCommentById(Integer commentId) {
            commentsRepository.deleteById(commentId);
        }

    }


