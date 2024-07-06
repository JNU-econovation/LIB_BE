package com.lib.book.dto;

import com.lib.book.Book;
import lombok.Getter;

@Getter
public class ReadBookDetailResponse {
    private Long bookId;
    private String bookCover;
    private String bookName;
    private String authorCate;
    private String publisher;
    private Integer rating;
    private boolean isAdded;
    private String bookExplain;

    public ReadBookDetailResponse(Book book){
        this.bookId=book.getBookId();
        this.bookCover =book.getBookCover();
        this.bookName =book.getBookName();
        this.authorCate=authorCate(book);
        this.publisher=book.getPublisher();
        this.rating=book.getRating();
        this.isAdded=book.isAdded();
        this.bookExplain=book.getBookExplain();
    }

    private String authorCate(Book book){
        return String.format("%s|%s", book.getAuthor(), book.getCategory().getCatetoryType());
    }
}
