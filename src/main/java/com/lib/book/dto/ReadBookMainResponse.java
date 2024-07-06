package com.lib.book.dto;

import com.lib.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadBookMainResponse {
    private Long bookId;
    private String bookCover;
    private String bookName;
    private String authorPub;
    public ReadBookMainResponse(Book book){
        this.bookId = book.getBookId();
        this.bookName=book.getBookName();
        this.bookCover=book.getBookCover();
        this.authorPub=authorPub(book);
    }

    private String authorPub(Book book){
        return String.format("%s/%s", book.getAuthor(), book.getPublisher());
    }
}
