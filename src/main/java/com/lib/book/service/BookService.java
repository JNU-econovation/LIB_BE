package com.lib.book.service;

import com.lib.book.Book;
import com.lib.book.BookRepository;
import com.lib.book.dto.*;
import com.lib.category.Category;
import com.lib.category.CategoryRepository;
import com.lib.member.Member;
import com.lib.openFeign.dto.AiRequest;
import com.lib.openFeign.dto.AiResponse;
import com.lib.openFeign.feign.RecommendationFeignClient;
import com.lib.record.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final RecommendationFeignClient recommendationFeignClient;
    private final RecordRepository recordRepository;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

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
    @Transactional(readOnly = true)
    public List<ReadBookMainResponse> findRandomBooksByCategory(String categoryType){
        System.out.println("여기에요 categoryType:"+categoryType);
        Category category1= categoryRepository.findByCategoryType(categoryType);
        System.out.println("여기에요(category객체)!:"+category1);

        List<Book> bookList = bookRepository.findRandomByCategory(category1.getCategoryId());
        System.out.println("여기에요(bookList)!:"+bookList);
        List<ReadBookMainResponse> responseList = bookList.stream()
                .map(ReadBookMainResponse::new) // Book 객체를 ReadBookMainResponse 객체로 변환
                .toList();//불변 객체
                //.collect(Collectors.toList()); -> 값 수정가능
        System.out.println("여기에요(responseList)!:"+responseList);
        return responseList;

    }
    @Transactional(readOnly = true)
    // (메인화면) 독자들이 좋아하는 책 조회
    public List<ReadBookMainResponse> findByBookRating(){
        List<Book> bookList = bookRepository.findByBookRating();
        List<ReadBookMainResponse> responseList = bookList.stream()
                .map(ReadBookMainResponse::new) // Book 객체를 ReadBookMainResponse 객체로 변환
                .toList();
        return responseList;
    }

    //책 정보 추가 메서드(create)(기록할때 같이 들어가야함)
    public void save(AddBookRequest book, Member memberId){
        Long categoryId = book.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IllegalArgumentException("not found"+categoryId));
        Book bookSave= bookRepository.save(book.toBookEntity(category));
        book.toRecordEntity(bookSave, memberId); //book과 record테이블에 값 넣기
    }
    //메인페이지 AI책 추천
    public List<MainAiRecommendResponse> findByAiRecommendMain(Member memberId){
        System.out.println("멤버 아이디:"+memberId);

        List<String> isbnRequest =bookRepository.findByMemberId(memberId);
        System.out.println("쿼리들어간 후:"+isbnRequest);
        if (isbnRequest.isEmpty()) {
            throw new IllegalArgumentException("not found" + memberId);
        };

        //프론트에서 백으로 요청-> 백은 해당 회원 아이디에 따른 관심 서재, 기록 테이블 속 책 id를 가져오고 (리스트)
        // 그 리스트를 반복문에 넣어서 isbn 리스트를 생성-> ai에 넘김
        AiRequest request = new AiRequest(isbnRequest); //ai에 보낼 isbn리스트 생성 완료
        AiResponse aiResponse= recommendationFeignClient.findAiRecommend(request);//응답

        List<String> isbnList= aiResponse.getRecommended_isbns();

        List<MainAiRecommendResponse> bookInfoList = new ArrayList<>();
        for (String isbn : isbnList) {
            Optional<Book> optionalBook = bookRepository.findByISBN(isbn);
            if (optionalBook.isPresent()) {
                Book eachBook = optionalBook.get();
                MainAiRecommendResponse bookInfo = new MainAiRecommendResponse(eachBook);
                bookInfoList.add(bookInfo);
            }
        }
        return bookInfoList;

    }
    //책 상세 페이지 AI책 추천
    public List<ReadBookMainResponse> findByAiRecommendDetail(Long bookId){
        Book book=bookRepository.findById(bookId).orElseThrow(()-> new IllegalArgumentException("not found"+bookId));
        String ISBN= book.getISBN();
        List<String> list = new ArrayList<>();
        list.add(ISBN);
        System.out.println("isbn:"+ISBN);
        AiRequest request = new AiRequest(list); //ai에 보낼 isbn리스트 생성 완료
        AiResponse aiResponse= recommendationFeignClient.findAiRecommend(request);//응답
        System.out.println("aiResponse:"+aiResponse);
        List<String> isbnList= aiResponse.getRecommended_isbns();

        List<ReadBookMainResponse> bookInfoList = new ArrayList<>();
        for (String isbn : isbnList) {
            Optional<Book> optionalBook = bookRepository.findByISBN(isbn);
            if (optionalBook.isPresent()) {
                Book eachBook = optionalBook.get();
                ReadBookMainResponse bookInfo = new ReadBookMainResponse(eachBook);
                bookInfoList.add(bookInfo);
            }
        }
        return bookInfoList;

    }

    //작성기록 조회에서 책정보 불러오기
    public ReadBookResponse findByBookId(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found:"+id));
        ReadBookResponse response = new ReadBookResponse(book);
        return response;
    }


    //삭제는 x 칼럼 is_deleted를 true로 바꾸는걸로)

}
