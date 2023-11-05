package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.entity.Ad;
import ru.skypro.homework.model.entity.User;

import java.util.List;

/**
 * Репозиторий для доступа к данным объявлений в БД
 */
@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {
    /**
     * Поиск объявлений по автору
     *
     * @param author автор объявления
     * @return список найденных объявлений
     */
    List<Ad> findByAuthor(User author);
}
