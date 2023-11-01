package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetAllAdsDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {
    private final AdsService adsService;
    private final ImageService imageService;

    @Operation(
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })

    @GetMapping("/{id}")
    public ResponseEntity<GetFullAdInfoDto> getAdInfo(@PathVariable Integer id) {
        try {
            GetFullAdInfoDto dto = adsService.getFullAdInfoDto(id);
            return ResponseEntity.ok().body(dto);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto createOrUpdateAdDto, @RequestPart("image") MultipartFile image,
                                        Principal principal) {
        try {
            AdsDto adsDto = adsService.createAd(createOrUpdateAdDto, image, principal.getName());
            return ResponseEntity.ok(adsDto);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(
            summary = "Обновление информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAd(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ResponseEntity.ok(new AdsDto());
    }

    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    ),
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdById(@PathVariable("id") Integer id, Principal principal) {
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            })
    @GetMapping
    public ResponseEntity<GetAllAdsDto> getAllAds() {
        try {
            GetAllAdsDto ads = adsService.getAllAds();
            return ResponseEntity.ok(ads);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(
            summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    ),
            })
    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateAdImage(@PathVariable Integer id,
                                                @RequestPart MultipartFile image) throws IOException {
        adsService.updateImage(id, image);
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/image/{id}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE
    })
    @Operation(
            summary = "Получить картинку объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })

    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {

        try {
            return ResponseEntity.ok(imageService.loadPictureFile(id));

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
