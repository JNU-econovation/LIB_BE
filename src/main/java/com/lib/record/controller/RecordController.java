package com.lib.record.controller;

import com.lib.book.dto.ReadBookDetailResponse;
import com.lib.record.dto.ReadRecordMainResponse;
import com.lib.record.dto.ReadRecordResponse;
import com.lib.record.service.RecordService;
import com.lib.utils.ApiResponse;
import com.lib.utils.ApiResponseGenerator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController

public class RecordController {
    private final RecordService recordService;

    //(기록페이지) 전체 기록 조회
    @GetMapping("/records/books")
    public ApiResponse<ApiResponse.CustomBody<List<ReadRecordResponse>>> findRecordWholeBook(){
        Long memberId = 1L;
        List<ReadRecordResponse> response= recordService.findRecordWholeBook(memberId);
        //(기록테이블)회원아이디를 이용해 '기록 내용', '기록 아이디','책 아이디' 찾기(리스트)
        //책 아이디 리스트를 반복문으로 돌려서 (책 테이블)'제목', '커버','저자' 값을 가져옴

        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

    //(메인페이지) 기록히스토리 조회
    @GetMapping("/rcords/main")
    public ApiResponse<ApiResponse.CustomBody<List<ReadRecordMainResponse>>> findRecordMain(){
        Long memberId = 1L;
        List<ReadRecordMainResponse> response=recordService.findRecordMainBook(memberId);
        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

    //(작성페이지) 책 정보및 기록 내용 조회
    /*@GetMapping("/records/{bookId}")
    public ApiResponse<ApiResponse.CustomBody<List<ReadBookDetailResponse>>> findBookDetail(@PathVariable("bookId") Long bookId){

    }
     */
}
