package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Override
    public Image upload(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setData(imageFile.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public byte[] getImage(Integer imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow();
        return image.getData();
    }

}
