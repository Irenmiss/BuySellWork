package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String addImage(MultipartFile image);

    byte[] loadImage(String fileName);

    byte[] loadPictureFile(String fileName);


}