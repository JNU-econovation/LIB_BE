package com.lib.book.dto;

import com.lib.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadBookDetailRequest {
    private Integer ISBN;
    public Book toEntity(){
        return Book.builder()
                .ISBN(ISBN)
                .build();
    }

}
