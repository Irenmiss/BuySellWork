package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Integer> {
    List<Ad> findByAuthor(User author);
    List<Ad> findByTitleContainingIgnoreCase(String title);
    Ad findAllByAuthorId(Integer id);
}
