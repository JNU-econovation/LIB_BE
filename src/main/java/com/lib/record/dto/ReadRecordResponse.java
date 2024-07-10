package com.lib.record.dto;

import com.lib.book.Book;
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
    private String bookName;
    private String bookCover;
    private String author;
    private String recordContent;

    public ReadRecordResponse (Book book, Record record){
        this.recordId=record.getRecordId();
        this.bookCover=book.getBookCover();
        this.bookName=book.getBookName();
        this.author=book.getAuthor();
        this.recordContent=record.getRecordContent();
    }


}
