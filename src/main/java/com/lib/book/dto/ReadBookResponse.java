package com.lib.book.dto;

import com.lib.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadBookResponse {
    private Long bookId;
    private String bookName;
    private String bookCover;
    private String authorCatePub;


    public ReadBookResponse(Book book){
        this.bookId=book.getBookId();
        this.bookName=book.getBookName();
        this.bookCover=book.getBookCover();
        this.authorCatePub=authorCatePub(book);
    }
    private String authorCatePub(Book book){
        return String.format("%s|%s|%s",book.getAuthor(), book.getCategory().getCatetoryType(), book.getPublisher());
    }
}