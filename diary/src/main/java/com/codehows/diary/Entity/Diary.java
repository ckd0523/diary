package com.codehows.diary.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @Builder
    public Diary(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

}
