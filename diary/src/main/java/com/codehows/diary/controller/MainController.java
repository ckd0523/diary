package com.codehows.diary.controller;

import com.codehows.diary.Entity.Diary;
import com.codehows.diary.dto.DiaryListViewResponse;
import com.codehows.diary.dto.DiaryViewResponse;
import com.codehows.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final DiaryService diaryService;

    @GetMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

    @GetMapping("/diaries")
    public String getDiaries(Model model) {
        List<DiaryListViewResponse> diaries = diaryService.findAll()
                .stream()
                .map(DiaryListViewResponse::new)
                .toList();
        model.addAttribute("diaries", diaries); // 블로그 글 리스트 저장
        return "diaryList"; // articleList.html 라는 뷰 조회
    }

    @GetMapping("/diaries/{id}")
    public String getDiary(@PathVariable Long id, Model model) {
        Diary diary = diaryService.findById(id);
        model.addAttribute("diary", new DiaryViewResponse(diary));
        return "diary";
    }

    @GetMapping("/new-diary")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id 는 없을 수도 있음)
    public String newDiary(@RequestParam(required = false) Long id, Model model, @RequestParam(required = false) LocalDate date) {
        if (id == null) { // id 가 없으면 생성
            model.addAttribute("diary", new DiaryViewResponse());
            model.addAttribute("date", date);
        } else {
            Diary diary = diaryService.findById(id);
            model.addAttribute("diary", new DiaryViewResponse(diary));
        }
        return "newDiary";
    }



}
