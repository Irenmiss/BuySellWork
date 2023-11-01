package ru.skypro.homework.mapper;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Service
public class CommentsMapperImpl implements CommentsMapper {

@Override
    public CommentsDto toCommentsDto(Comment entity) {

    CommentsDto dto = new CommentsDto();
    dto.setAuthor(entity.getAuthor().getId());
    dto.setAuthorFirstName(entity.getAuthor().getFirstName());
    dto.setPk(entity.getId());
    dto.setText(entity.getText());

    if (entity.getAuthor().getImage() != null) {
        dto.setAuthorImage(String.format("/ads/image/%s", entity.getAuthor().getImage()));
    } else {
        dto.setAuthorImage(null);
    }
    dto.setCreatedAt(LocalDateTime.ofInstant(entity.getCreatedAt(), TimeZone.getDefault().toZoneId()));

    return dto;
}
@Override
    public Comment toCommentsEntity(CreateOrUpdateCommentDto dto, User author, Ad ad) {

        Comment entity = new Comment();
        entity.setText(dto.getText());
        entity.setAd(ad);
        entity.setAuthor(author);
//        entity.setCreatedAt(Instant.now());
        entity.setCreatedAt(Instant.now());

        return entity;
    }
}

