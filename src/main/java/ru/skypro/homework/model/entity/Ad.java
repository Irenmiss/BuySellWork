package ru.skypro.homework.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Сущность объявления
 * Соответствует таблице "ads" в БД
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Integer pk;
    @Column(name = "ad_title")
    private String title;
    @Column(name = "ad_description")
    private String description;
    @Column(name = "ad_price")
    private Integer price;

    @Column(name = "image_id")
    private String image;

    //ключ на таблицу "users"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    public Integer getPk() {
        return pk;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public User getAuthor() {
        return author;
    }
}
