package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllCommentsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.CommentsService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Data
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
//    private final UsersRepository usersRepository;
//    private final CommentsRepository commentsRepository;
//    private final CommentsMapper commentsMapper;
//    private final AdsRepository adsRepository;
//
//    @Override
//    public List <CommentsDto> getAdComments (Integer id) {
//        return commentsMapper.toCommentsDto(commentsRepository.findAllByAdId(id));
//    }
//    @Override
//    public Comment addCommentToAd (Integer id , CreateOrUpdateCommentDto createOrUpdateCommentDto , Authentication authentication) {
//        Ad ad = adsRepository.findById(id).orElseThrow();
//        User user = usersRepository.findByEmail (SecurityContextHolder.getContext ()
//                .getAuthentication ().getName ()).orElseThrow ();
//        Comment comment = commentsMapper.toCommentyCr (createOrUpdateCommentDto);
//        comment.setAuthor (user);
//        comment.setAd(ad);
//        comment.setCreatedAt(Instant.now());
//        return commentsRepository.save(comment);
//    }
//    @Override
//    public void deleteComment (Integer adId , Integer commentId) {
//        Comment comment = commentsRepository.findAllByAdId (adId).get (commentId);
//        commentsRepository.delete(comment);
//    }
//
//    @Override
//    public Comment updateComment (Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
//        Comment comment = commentsRepository.findAllByAdId (adId).get (commentId);
//        comment.setText(createOrUpdateCommentDto.getText());
//        return commentsRepository.save(comment);
//    }
    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;
    private final CommentsMapper commentsMapper;
    private final UsersRepository usersRepository;
    @Override
    public GetAllCommentsDto getComments(int id) {
        List<Comment> comments = commentsRepository.findAllByAdsId(id);
        return commentsMapper.listCommentToCommentDto(comments.size(), comments);
    }

    @Override
    public CommentsDto addComment(int id, CreateOrUpdateCommentDto text) {
        User user = usersRepository
                .findByEmail(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow();
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setAd(adsRepository.findById(id).orElseThrow());
        comment.setText(text.getText());
        return commentsMapper.commentToCommentDto(commentsRepository.save(comment));
    }
}

