package com.lib.record.controller;

import com.lib.book.dto.ReadBookDetailResponse;
import com.lib.book.dto.ReadBookResponse;
import com.lib.book.service.BookService;
import com.lib.record.dto.ReadRecordMainResponse;
import com.lib.record.dto.ReadRecordResponse;
import com.lib.record.service.RecordService;
import com.lib.utils.ApiResponse;
import com.lib.utils.ApiResponseGenerator;
import com.lib.utils.JwtRequired;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController

public class RecordController {
    private final RecordService recordService;
    private final BookService bookService;

    //(기록페이지) 전체 기록 조회
    @JwtRequired
    @GetMapping("/records/books")
    public ApiResponse<ApiResponse.CustomBody<List<ReadRecordResponse>>> findRecordWholeBook(){
        Long memberId = 1L;
        List<ReadRecordResponse> response= recordService.findRecordWholeBook(memberId);
        //(기록테이블)회원아이디를 이용해 '기록 내용', '기록 아이디','책 아이디' 찾기(리스트)
        //책 아이디 리스트를 반복문으로 돌려서 (책 테이블)'제목', '커버','저자' 값을 가져옴

        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

    //(메인페이지) 기록히스토리 조회
    @JwtRequired
    @GetMapping("/records/main")
    public ApiResponse<ApiResponse.CustomBody<List<ReadRecordMainResponse>>> findRecordMain(){
        Long memberId = 1L;
        List<ReadRecordMainResponse> response=recordService.findRecordMainBook(memberId);
        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

    // 작성 페이지 책정보 조회
    @JwtRequired
    @GetMapping("/records/{bookId}")
    public ApiResponse<ApiResponse.CustomBody<ReadBookResponse>>findBookContent(@PathVariable("bookId") Long bookId){
        ReadBookResponse response= bookService.findByBookId (bookId);

        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }

//    // 기록 작성( 책정보 존재)
//    @JwtRequired
//    @PostMapping("/records/{bookId}")
//    public ResponseEntity<?> addRecordRequest(@PathVariable("bookId") Long bookId){
//        RecordService response=recordService.findRecordMainBook(bookId);
//
//        return ApiResponseGenerator.success(response, HttpStatus.OK);
//
//    }

    // 상세 기록 내용 조회
//    @GetMapping("/records/{recordId}")
//    @JwtRequired
//    public ApiResponse<ApiResponse.CustomBody<List<ReadRecordDetailResponse>>>findBookContent(@PathVariable("recordId") Long recordId){
//        //서재 아이디를 통해 서재 테이블에서 책과 회원 아이디를 가져옴-> (책) 책 정보를 가져옴/ (기록내용) 기록테이블에서 회원과 책 아이디를 통해 기록 내용을 가져옴(별점, 기록 내용)
//        //각각 책&기록 내용 추가해줌
//    }

    // 나의 서재리스트
    //@GetMapping("/bookshelves")
}
