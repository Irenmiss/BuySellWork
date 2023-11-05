package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.entity.Comment;

import java.util.List;

/**
 * Репозиторий для доступа к данным комментариев в БД
 */
@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
    /**
     * Удаление всех комментариев объявления объявления.
     *
     * @param id идентификатор объявления.
     */

    void deleteAllByAdPk(Integer id);

    /**
     * Поиск комментария по идентификатору объявления
     *
     * @param id идентификатор объявления
     * @return список найденных комментариев
     */
    List<Comment> findByAdPk(Integer id);

}
