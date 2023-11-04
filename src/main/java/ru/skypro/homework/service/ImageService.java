package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для работы с изображениями
 */

public interface ImageService {
    /**
     * Добавление нового изображения к объявлению или в качестве аватара
     * {@link ru.skypro.homework.service.impl.ImageServiceImpl#addImage(MultipartFile)} )}
     *
     * @param image загружаемое изображение
     * @return сгенерированное методом название файла, которое будет помещено в БД
     */
    String addImage(MultipartFile image);

    /**
     * Загружает изображение по названию изображения
     * {@link ru.skypro.homework.service.impl.ImageServiceImpl#loadImage(String)}
     *
     * @param fileName название изображения
     * @return изображение в виде byte[]
     */

    byte[] loadImage(String fileName);
}