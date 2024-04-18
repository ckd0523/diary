package com.codehows.diary.dto;

import com.codehows.diary.Entity.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Getter
public class DiaryViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDate date;

    public DiaryViewResponse(Diary diary) {
        this.id = diary.getId();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.date = diary.getDate();
    }


}
