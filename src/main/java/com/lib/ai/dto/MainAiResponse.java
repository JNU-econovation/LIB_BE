package com.lib.ai.dto;

import com.lib.book.Book;
import lombok.Getter;

@Getter
public class MainAiResponse {
    private Long bookId;
    private String bookCover;


    public MainAiResponse(Book book){
        this.bookId=book.getBookId();
        this.bookCover=book.getBookCover();
    }
}
