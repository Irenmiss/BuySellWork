package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
