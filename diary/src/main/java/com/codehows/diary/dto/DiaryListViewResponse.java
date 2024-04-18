package com.codehows.diary.dto;

import com.codehows.diary.Entity.Diary;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class DiaryListViewResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDate date;

    public DiaryListViewResponse(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.date = diary.getDate();
    }

}
