package com.lib.record.dto;

import com.lib.book.Book;
import com.lib.category.Category;
import com.lib.member.Member;
import com.lib.record.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class ReadRecordResponse {
    private Long recordId;//
    private String bookCover;
    private String bookName;
    private String author;
    private String recordContent;

    public static ReadRecordResponse from(Book book, Record record){
        return ReadRecordResponse.builder()
                .bookCover(book.getBookCover())
                .bookName(book.getBookName())
                .author(book.getAuthor())
                .recordId(record.getRecordId())
                .recordContent(record.getRecordContent())
                .build();
    }

}
