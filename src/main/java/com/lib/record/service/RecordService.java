package com.lib.record.service;

import com.lib.book.Book;
import com.lib.book.BookRepository;
import com.lib.record.Record;
import com.lib.record.RecordRepository;
import com.lib.record.dto.ReadRecordMainResponse;
import com.lib.record.dto.ReadRecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final BookRepository bookRepository;
    private final RecordRepository recordRepository;

    //(마이페이지)기록 전체 조회(read)
    //(기록테이블)회원아이디를 이용해 '기록 내용', '기록 아이디','책 아이디' 찾기(리스트)
    //책 아이디 리스트를 반복문으로 돌려서 (책 테이블)'제목', '커버','저자' 값을 가져옴
    public List<ReadRecordResponse> findRecordWholeBook(Long memberId){

        System.out.println("기록페이지 전체기록 리스트 before: "+memberId);
        List<Record> recordList =recordRepository.findByMemberId(memberId);
        System.out.println("기록페이지 전체기록 리스트 after: "+recordList);
        if (recordList.isEmpty()){
            throw new IllegalArgumentException("not found"+memberId);
        }


        List<ReadRecordResponse> responseList=new ArrayList<>();
        for (Record record : recordList) {
            Book eachBook = bookRepository.findAllBooks(record.getBook().getBookId()).orElseThrow();
            ReadRecordResponse response =new ReadRecordResponse(eachBook, record);
            System.out.println("기록 리스트22: "+response);
            responseList.add(response);
        }
        return responseList;
    }

    //(메인페이지-5개)기록 조회(read)
    public List<ReadRecordMainResponse> findRecordMainBook(Long memberId){

        List<Record> recordList =recordRepository.findByMemberId(memberId);
        System.out.println("메인 페이지 기록 조회" + recordList);
        if (recordList.isEmpty()){
            throw new IllegalArgumentException("not found"+memberId);
        }


        List<ReadRecordMainResponse> responseList=new ArrayList<>();
        for (Record record : recordList) {
            Book eachBook = bookRepository.findAllBooks(record.getBook().getBookId()).orElseThrow();
            ReadRecordMainResponse response =new ReadRecordMainResponse(eachBook, record);
            System.out.println("여기에요!!-----------" + response);
            responseList.add(response);
        }
        return responseList;
    }

    //세부 기록 조회(read)

    //기록 작성(create)

    //기록 삭제(delete)

    //기록 수정(update)
}
