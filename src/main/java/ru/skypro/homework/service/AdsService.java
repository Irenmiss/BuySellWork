package ru.skypro.homework.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.GetAllAdsDto;
import ru.skypro.homework.dto.GetFullAdInfoDto;
import ru.skypro.homework.service.impl.AdsServiceImpl;

/**
 * Сервис для работы с объявлениями
 */

public interface AdsService {
    /**
     * Создание объявления
     * {@link ru.skypro.homework.service.impl.AdsServiceImpl#createAd(CreateOrUpdateAdDto, MultipartFile, String)}
     *
     * @param createOrUpdateAdDto данные о товаре в виде DTO
     * @param image               изображение товара товара
     * @param userDetails         информация о пользователе
     */
    AdsDto createAd(CreateOrUpdateAdDto createOrUpdateAdDto,
                    MultipartFile image,
                    String userDetails);

    /**
     * Редактирование выбранного по идентификатору объявления
     * {@link ru.skypro.homework.service.impl.AdsServiceImpl#updateAd(Integer, CreateOrUpdateAdDto, String)}
     *
     * @param id                  идентификатор объявления
     * @param createOrUpdateAdDto данные о товаре в виде DTO
     * @param userDetails         информация о пользователе
     * @return обновленная информация об объявлении
     */

    @Transactional
    AdsDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String userDetails);

    /**
     * Удаление объявления по идентификатору
     * {@link ru.skypro.homework.service.impl.AdsServiceImpl#deleteById(Integer, String)} )}
     *
     * @param id          идентификатор объявления
     * @param userDetails информация о пользователе
     * @return true или false
     */

    boolean deleteById(Integer id, String userDetails);

    /**
     * Получение полной информации об объяалении по его идентификатору
     * {@link ru.skypro.homework.service.impl.AdsServiceImpl#getFullAdInfoDto(Integer)} )}
     *
     * @param id идентификатор объявления
     * @return полученная информация
     */

    GetFullAdInfoDto getFullAdInfoDto(Integer id);

    /**
     * Получить все объявления
     * {@link AdsServiceImpl#getAllAds()}
     *
     * @return объявления
     */

    GetAllAdsDto getAllAds();

    /**
     * Изменение изображения объявления, выбранного по идентификатору
     * {@link AdsServiceImpl#updateImage(Integer, MultipartFile)}
     *
     * @param id    идентификатор объявления
     * @param image новая картинка объявления
     * @return true или false
     */

    boolean updateImage(Integer id, MultipartFile image);

    /**
     * Просмотр авторизованным пользователем своих объявлений
     * {@link ru.skypro.homework.service.impl.AdsServiceImpl#getAllMyAds(String)}
     *
     * @param userDetails информация о пользователе
     * @return список объявлений пользователя
     */

    GetAllAdsDto getAllMyAds(String userDetails);
}
