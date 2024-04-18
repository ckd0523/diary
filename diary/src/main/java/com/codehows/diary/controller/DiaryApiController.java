package com.codehows.diary.controller;


import com.codehows.diary.Entity.Diary;
import com.codehows.diary.dto.AddDiaryRequest;
import com.codehows.diary.dto.DiaryResponse;
import com.codehows.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryApiController {

    private final DiaryService diaryService;

    @PostMapping("/api/diaries")
    public ResponseEntity<Diary> addDiary(@RequestBody AddDiaryRequest request) {

        Diary savedDiary = diaryService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDiary);

    }

    @GetMapping("/api/diaries")
    public ResponseEntity<List<DiaryResponse>> findAllDiaries() {
        List<DiaryResponse> diaries = diaryService.findAll()
                .stream()
                .map(DiaryResponse::new)
                .toList();
        return ResponseEntity.ok().body(diaries);
    }

    @GetMapping("/api/diaries/{id}")
    // URL 경로에서 값 추출
    public ResponseEntity<DiaryResponse> findDiaryById(@PathVariable Long id) {
        Diary diary = diaryService.findById(id);

        return ResponseEntity.ok().body(new DiaryResponse(diary));
    }

    @DeleteMapping("/api/diaries/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
