package com.lib.book.dto;

import com.lib.book.Book;
import lombok.Getter;

@Getter
public class MainAiRecommendResponse {
    private Long bookId;
    private String bookCover;


    public MainAiRecommendResponse(Book book){
        this.bookId=book.getBookId();
        this.bookCover=book.getBookCover();
    }
}
