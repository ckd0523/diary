package com.codehows.diary.controller;

import com.codehows.diary.dto.DiaryListViewResponse;
import com.codehows.diary.service.DiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    DiaryService diaryService;

    @GetMapping("/")
    public String index() {
        return "calendar";
    }

    @GetMapping("/diaries")
    public String getArticles(Model model) {
        List<DiaryListViewResponse> diaries = diaryService.findAll()
                .stream()
                .map(DiaryListViewResponse::new)
                .toList();
        model.addAttribute("diaries", diaries); // 블로그 글 리스트 저장
        return "calendar"; // articleList.html 라는 뷰 조회
    }

}
