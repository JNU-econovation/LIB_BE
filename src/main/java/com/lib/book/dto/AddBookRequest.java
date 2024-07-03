package com.lib.book.dto;

import com.lib.book.Book;
import com.lib.category.Category;
import com.lib.record.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {
    private String bookCover;
    private String bookName;
    private String author;
    private Long categoryId;
    private String publisher;
    private Integer rating;
    private String recordContent;

    public Book toBookEntity(Category category){
        return Book.builder()
            .bookCover(bookCover)
            .bookName(bookName)
            .author(author)
            .category(category)
            .publisher(publisher)
            .rating(rating)//나중에 바꾸기(평균)
            .isAdded(false)//나중에 바꾸기
            .customed(true)
                .build();

    }
    public Record toRecordEntity(Book book){
        return Record.builder()
                .rating(rating)
                .recordContent(recordContent)
                .book(book)
                .build();
    }
}
