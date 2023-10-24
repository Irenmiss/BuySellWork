package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(name = "image_path")
    private String imagePath;

    //ключ на таблицу "users"
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Integer getPk() {
        return pk;
    }
}
