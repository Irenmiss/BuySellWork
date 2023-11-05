package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.entity.User;

/**
 * Репозиторий для доступа к данным пользователей в БД
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    /**
     * Проверка пользователя по юзернейму
     *
     * @param username username пользователя
     * @return найденный пользователь
     */
    User findByUsername(String username);
}
