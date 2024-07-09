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
public class ReadRecordDetailResponse {
    private Long recordId;
    private String bookCover;
    private String bookName;
    private String authorCatePub;
    private Integer rating;
    private String recordContent;

    public ReadRecordDetailResponse(Book book, Record record){
        this.recordId=record.getRecordId();
        this.bookCover=book.getBookCover();
        this.bookName=book.getBookName();
        this.authorCatePub=authorCatePub(book);
        this.rating=book.getRating();
        this.recordContent=record.getRecordContent();
    }

    private String authorCatePub(Book book){
        return String.format("%s|%s|%s", book.getAuthor(),book.getCategory().getCatetoryType(),book.getPublisher());
    }


}
