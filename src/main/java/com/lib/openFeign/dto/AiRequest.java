package com.lib.openFeign.dto;

import com.lib.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AiRequest {
    private List<String> isbn_list;
}
