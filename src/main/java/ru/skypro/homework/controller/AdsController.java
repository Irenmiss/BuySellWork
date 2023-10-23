package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.service.AdsService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {
    @Operation(
            summary = "Получение информации об объявлении"
    )
    @GetMapping("/{id}")
    public ResponseEntity<GetFullAdInfoDto> getAdInfo(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new GetFullAdInfoDto());
    }
    @Operation(
            summary = "Добавление объявления"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto createOrUpdateAdDto, @RequestPart("image") MultipartFile image) {
        return new ResponseEntity<>(new AdsDto(), HttpStatus.CREATED);
    }
    @Operation(
            summary = "Изменение информации об объявлении"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAd(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ResponseEntity.ok(new AdsDto());
    }
    @Operation(
            summary = "Удаление объявления"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdById(@PathVariable("id") Integer id) {
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получение всех объявлений"
    )
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(new AdsDto());
    }
}
