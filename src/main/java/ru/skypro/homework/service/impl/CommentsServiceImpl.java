package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.mapper.CommentsMapper;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.service.CommentsService;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private CommentsRepository commentsRepository;
    private CommentsMapper commentsMapper;
    @Override
    public List<CommentsDto> getCommentsByIdAd(Integer id) {
        return commentsMapper.toCommentsDto(commentsRepository.findByAdId(id));
    }

}

