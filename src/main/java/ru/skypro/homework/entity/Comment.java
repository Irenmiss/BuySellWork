package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Integer id;
    @Column(name = "com_text")
    private String text;
    @Column(name = "creation_time")
    private Instant createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    //на таблицу ads
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;
}

