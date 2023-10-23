package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
//    Optional<Comment> findByIdAndAdId(Integer id, Integer adId);
//    List<Comment> findAllByAdId(Integer id);

    Optional<Comment> deleteAdsComment(Integer adsId, Integer id);
    //можно использовать такой запрос к базеБ, что бы сразу удалять без логики в сервисе?

    //вроде в задании говорили что надо использовать сортировку...
    List<Comment> findAllByAdsIdOrderByIdDesc(Integer adsId);
    List<Comment> findAllByAdsId(int id);

}
