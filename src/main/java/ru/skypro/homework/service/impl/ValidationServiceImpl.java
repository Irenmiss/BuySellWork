package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.service.ValidationService;

/**
 * Реализация бизнес-логики по проверке сущностей
 */
@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Object object) {

        if (object instanceof CreateOrUpdateAdDto) {
            return ((CreateOrUpdateAdDto) object).getDescription() != null
                    && ((CreateOrUpdateAdDto) object).getTitle() != null
                    && ((CreateOrUpdateAdDto) object).getPrice() != 0;

        } else if (object instanceof RegisterUserDto) {
            return ((RegisterUserDto) object).getUsername() != null
                    && ((RegisterUserDto) object).getPassword() != null
                    && ((RegisterUserDto) object).getFirstName() != null
                    && ((RegisterUserDto) object).getLastName() != null
                    && ((RegisterUserDto) object).getPhone() != null;

        } else if (object instanceof CreateOrUpdateCommentDto) {
            return ((CreateOrUpdateCommentDto) object).getText() != null;
        }

        return false;
    }
}
