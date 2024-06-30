package com.lib.book.controller;

import com.lib.book.Book;
import com.lib.book.dto.AddBookRequest;
import com.lib.book.dto.ReadBookDetailRequest;
import com.lib.book.dto.ReadBookDetailResponse;
import com.lib.book.dto.ReadBookMainResponse;
import com.lib.book.service.BookService;
import com.lib.category.Category;
import com.lib.utils.ApiResponse;
import com.lib.utils.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<ApiResponse.CustomBody<List<ReadBookMainResponse>>> findCateBook(@RequestParam("categoryId") Long categoryId){

        List<ReadBookMainResponse> responseList = bookService.findRandomBooksByCategory(categoryId);

        return ApiResponseGenerator.success(responseList, HttpStatus.OK);
    }

    // (메인화면) 독자들이 좋아하는 책 조회
    @GetMapping("/books/popular")
    public ApiResponse<ApiResponse.CustomBody<List<ReadBookMainResponse>>> findPopBook() {
        List<ReadBookMainResponse> responseList = bookService.findByBookRating();
        return ApiResponseGenerator.success(responseList, HttpStatus.OK);
    }

    //책 정보 추가 메서드(create)(기록할때 같이 들어가야함)
    @PostMapping("/records")
    public ApiResponse<ApiResponse.CustomBody<AddBookRequest>> addBookRequest(@RequestBody AddBookRequest book){
        Book savedBook = bookService.save(book);
        return ApiResponseGenerator.success(savedBook, HttpStatus.OK)
    }
    //책 삭제(기록이 삭제될때 같이 삭제됨)

}
