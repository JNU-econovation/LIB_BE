package com.lib.openFeign.dto;

import com.lib.book.Book;
import lombok.Data;
import lombok.Getter;

import java.util.List;
@Getter
public class AiResponse {
    private List<String> recommended_isbns;
}
