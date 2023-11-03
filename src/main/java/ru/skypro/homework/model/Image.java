package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Модель для создания объектов изображений с дальнейшием помещением информации о них в БД
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String id;
    @Lob
    private byte[] data;

    public String getId() {
        return id;
    }
}
