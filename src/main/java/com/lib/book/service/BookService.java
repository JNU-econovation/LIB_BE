package com.lib.book.service;

import com.lib.book.Book;
import com.lib.book.BookRepository;
import com.lib.book.dto.ReadBookDetailResponse;
import com.lib.book.dto.ReadBookMainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    //(상세페이지)책 정보 조회
    public ReadBookDetailResponse findById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found:"+id));
        ReadBookDetailResponse response = new ReadBookDetailResponse(book);
        return response;
    }
    // (메인화면) 카테고리별 책 조회

    public List<ReadBookMainResponse> findRandomBooksByCategory(Long categoryId){
       List<Book> bookList = bookRepository.findRandomByCategory(categoryId);
        List<ReadBookMainResponse> responseList = bookList.stream()
                .map(ReadBookMainResponse::new) // Book 객체를 ReadBookMainResponse 객체로 변환
                .toList();//불변 객체
                //.collect(Collectors.toList()); -> 값 수정가능
        return responseList;

    }

    // (메인화면) 독자들이 좋아하는 책 조회
    public List<ReadBookMainResponse> findByBookRating(){
        List<Book> bookList = bookRepository.findByBookRating();
        List<ReadBookMainResponse> responseList = bookList.stream()
                .map(ReadBookMainResponse::new) // Book 객체를 ReadBookMainResponse 객체로 변환
                .toList();
        return responseList;
    }

    //책 정보 추가 메서드(create)(기록할때 같이 들어가야함)
    public Book save(AddBookRequest book){
        return bookRepository.save(book.toEntity());
    }

    //삭제는 고민(사용자 등록책이 삭제되면 책도 삭제할것인가?)

}
