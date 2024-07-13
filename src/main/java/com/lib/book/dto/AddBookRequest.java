package com.lib.book.dto;

import com.lib.book.Book;
import com.lib.category.Category;
import com.lib.member.Member;
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
    private Integer rating;
    private String recordContent;
    private Long memberId;

    public Book toBookEntity(Category category){
        return Book.builder()
            .bookCover(bookCover)
            .bookName(bookName)
            .author(author)
            .category(category)
            .rating(rating)//나중에 바꾸기(평균)
            .customed(true)
                .build();

    }
    public Record toRecordEntity(Book book, Member memberId){
        return Record.builder()
                .rating(rating)
                .recordContent(recordContent)
                .book(book)
                .member(memberId)
                .build();
    }
}
