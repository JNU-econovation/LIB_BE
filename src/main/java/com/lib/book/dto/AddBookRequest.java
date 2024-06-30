package com.lib.book.dto;

import com.lib.book.Book;
import com.lib.category.Category;

public class AddBookRequest {
    private String bookCover;
    private String bookName;
    private String author;
    private Category category;
    private String publisher;
    //private Integer memberId;//필요없나
    private Integer rating;


    //recordContent를 넣어야하는데 record테이블에 있는 것임
    public AddBookRequest (Book book){
        this.bookCover =book.getBookCover();
        this.bookName =book.getBookName();
        this.author =book.getAuthor();
        this.category=book.getCategory();
        this.publisher=book.getPublisher();
        this.rating=book.getRating();
    }
}
