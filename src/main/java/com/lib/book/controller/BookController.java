package com.lib.book.controller;

import com.lib.book.dto.AddBookRequest;
import com.lib.book.dto.MainAiRecommendResponse;
import com.lib.book.dto.ReadBookDetailResponse;
import com.lib.book.dto.ReadBookMainResponse;
import com.lib.book.service.BookService;
import com.lib.member.Member;
import com.lib.utils.ApiResponse;
import com.lib.utils.ApiResponseGenerator;
import com.lib.utils.JwtRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    //(상세페이지)책 정보 조회
    @GetMapping("/books/{bookId}")
    public ApiResponse<ApiResponse.CustomBody<ReadBookDetailResponse>> findDetailedBook(@PathVariable("bookId") Long id){
        ReadBookDetailResponse response=bookService.findById(id);

        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }


    // (메인화면) 카테고리별 책 조회
    @GetMapping("/books/categorys")
    public ApiResponse<ApiResponse.CustomBody<List<ReadBookMainResponse>>> findCateBook(@RequestParam("categoryType") String categoryType){
        List<ReadBookMainResponse> responseList = bookService.findRandomBooksByCategory(categoryType);
        return ApiResponseGenerator.success(responseList, HttpStatus.OK);
    }

    // (메인화면) 독자들이 좋아하는 책 조회
    @GetMapping("/books/popular")
    public ApiResponse<ApiResponse.CustomBody<List<ReadBookMainResponse>>> findPopBook() {
        List<ReadBookMainResponse> responseList = bookService.findByBookRating();
        return ApiResponseGenerator.success(responseList, HttpStatus.OK);
    }
    //책 정보 추가 메서드(create)(기록할때 같이 들어가야함)
    @JwtRequired
    @PostMapping("/records")
    public ResponseEntity<?> addBookRequest(@RequestBody AddBookRequest book, HttpServletRequest request){
        Member memberId=(Member) request.getAttribute("memberId");
        bookService.save(book, memberId);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

    //상세페이지 ai 요청
    @GetMapping("recommend/detail/{bookId}")
    public ApiResponse<ApiResponse.CustomBody<List<ReadBookMainResponse>>>findDetailAiBook(@PathVariable("bookId") Long bookId){
        List<ReadBookMainResponse> responseList=bookService.findByAiRecommendDetail(bookId);
        return ApiResponseGenerator.success(responseList, HttpStatus.OK);
    }

    //메인페이지 ai 요청
    @JwtRequired
    @GetMapping("/recommend/main")
    public ApiResponse<ApiResponse.CustomBody<List<MainAiRecommendResponse>>>findMainAiBook(HttpServletRequest request){
        Member memberId=(Member)request.getAttribute("memberId");
        //Long memberId = 1L;//기존
        List<MainAiRecommendResponse> responseList=bookService.findByAiRecommendMain(memberId);
        System.out.println("responseList(bookController 속 메인 ai요청):"+responseList);
        return ApiResponseGenerator.success(responseList, HttpStatus.OK);
    }


    //책 삭제(기록이 삭제될때 같이 삭제됨)

}
