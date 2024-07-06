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
public class ReadRecordMainResponse {
    private Long recordId;//
    private String bookName;
    private String bookCover;
    private String author;
    private String recordContent;

    public ReadRecordMainResponse (Book book, Record record){
        this.recordId=record.getRecordId();
        this.bookCover=book.getBookCover();
        this.recordContent=record.getRecordContent();
        this.bookName=book.getBookName();//
        this.author=book.getAuthor();//

    }

}
