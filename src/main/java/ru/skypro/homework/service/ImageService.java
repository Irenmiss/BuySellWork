package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String addImage(MultipartFile picture);

    byte[] loadImage(String fileName);

    byte[] loadImageFail(String fileName);
}