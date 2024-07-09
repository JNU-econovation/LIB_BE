//package com.lib.record.dto;
//import com.lib.book.Book;
//import com.lib.member.Member;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//public class addRecordRequest{
//    private String recordContent;
//    private Integer rating;
//    private Long id;
//    private Long bookId;
//
//    public Record recordEntity(Member member, Book book){
//        return Record.builder()
//                .recordContent(recordContent)
//                .rating(rating)
//                .id(id)
//                .bookId(bookId)
//                .build();
//    }