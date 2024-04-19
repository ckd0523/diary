package com.codehows.diary.dto;

import com.codehows.diary.Entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddDiaryRequest {

    private String title;
    private String content;
    private LocalDate date;

    public Diary toEntity() {
        return Diary.builder()
                .title(title)
                .content(content)
                .date(date)
                .build();
    }

}
