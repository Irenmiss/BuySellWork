package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService {

    Image upload(MultipartFile imageFile) throws IOException;

    byte[] getImage(Integer imageId);
}