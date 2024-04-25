package com.codehows.diary.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    private LocalDate date;

    @Column(name = "author", nullable = false)
    private String author;

    @Builder
    public Diary(String author, Long id, String title, String content, LocalDate date) {
        this.author = author;
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
