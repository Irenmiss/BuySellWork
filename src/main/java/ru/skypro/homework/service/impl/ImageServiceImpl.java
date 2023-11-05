package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.service.ImageService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;

import static java.nio.file.Files.*;

/**
 * Реализация бизнес-логики по работе с изображениями
 */

@Slf4j
@Service
@Data
@AllArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {
    private final String desktopPath = System.getProperty("user.dir") + File.separator + "images";

    @Override
    public String addImage(MultipartFile imageFile) {
        String type = imageFile.getContentType();
        type = type.replace("image/", ".");
        Image image = new Image();
        try {
            String fileName = UUID.randomUUID() + type;
            image.setId(fileName);
            createDirectories(Paths.get(desktopPath));
            imageFile.transferTo(new File(desktopPath + File.separator + fileName));
        } catch (IOException e) {
        }
        return image.getId();
    }

    @Override
    public byte[] loadImage(String fileName) {

        File image;
        byte[] outputFileBytes = null;
        try {
            image = new File(desktopPath, fileName);
            if (exists(image.toPath())) {
                outputFileBytes = readAllBytes(image.toPath());
            } else {
                try (InputStream in = new URL("").openStream()) {
                    outputFileBytes = in.readAllBytes();
                }
            }
        } catch (IOException e) {
        }
        return outputFileBytes;
    }
}
