package com.codehows.diary.service;

import com.codehows.diary.Entity.Diary;
import com.codehows.diary.dto.AddDiaryRequest;
import com.codehows.diary.dto.UpdateDiaryRequest;
import com.codehows.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Diary save(AddDiaryRequest request) {
        return diaryRepository.save(request.toEntity());
    }

    public List<Diary> findAll() {
        return diaryRepository.findAll();
    }

    public Diary findById(Long id) {

        return diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : "+id));
    }

    public void delete(Long id){
        diaryRepository.deleteById(id);
    }

    @Transactional
    public Diary update(Long id, UpdateDiaryRequest request){
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : "+id));
        diary.update(request.getTitle(), request.getContent());

        return diary;
    }

}
