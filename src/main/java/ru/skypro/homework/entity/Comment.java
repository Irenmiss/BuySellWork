package ru.skypro.homework.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "com_id")
//    private Integer pk;
//    @Column(name = "com_text")
//    private String text;
//
//    @ManyToOne
//    @JoinColumn(name = "ad_id")
//    private Ad ad;
//
//    @Column(name = "creation_time")
//    private LocalDateTime createdAt;
//    @JoinColumn(name = "user_id")
//    @ManyToOne(fetch = FetchType.EAGER)
////    private User user;
//    private User author;
//
//    public void setPk(Integer pk) {
//        this.pk = pk;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public void setAd(Ad ad) {
//        this.ad = ad;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "ad_id")
private Ad ad;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    // для автоматической установки метки времени при первом сохранении
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "text", nullable = false, length = 1000)
    private String text;

}

