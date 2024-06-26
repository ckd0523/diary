package com.codehows.diary.dto;

import com.codehows.diary.Entity.Diary;
import lombok.Getter;


import java.time.LocalDate;
import java.util.Date;

@Getter
public class DiaryResponse {

    private final String title;
    private final String content;
    private final LocalDate date;
    private final Long id;
    private final String author;

    public DiaryResponse(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.date = diary.getDate();
        this.author = diary.getAuthor();
    }

}
