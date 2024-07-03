package com.lib.book.service;

import com.lib.book.Book;
import com.lib.book.BookRepository;
import com.lib.book.dto.AddBookRequest;
import com.lib.book.dto.MainAiRecommendResponse;
import com.lib.book.dto.ReadBookDetailResponse;
import com.lib.book.dto.ReadBookMainResponse;
import com.lib.category.Category;
import com.lib.category.CategoryRepository;
import com.lib.openFeign.dto.AiRequest;
import com.lib.openFeign.dto.AiResponse;
import com.lib.openFeign.feign.RecommendationFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final RecommendationFeignClient recommendationFeignClient;

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
    public void save(AddBookRequest book){
        Long categoryId = book.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IllegalArgumentException("not found"+categoryId));
        Book bookSave= bookRepository.save(book.toBookEntity(category));
        book.toRecordEntity(bookSave); //book과 record테이블에 값 넣기
    }
    //메인페이지 AI책 추천
    public List<MainAiRecommendResponse> findByAiRecommendMain(Long memberId){
        List<String> isbnRequest =bookRepository.findByMemberId(memberId);
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
//        Book book=bookRepository.findById(bookId).orElseThrow(()-> new IllegalArgumentException("not found"+bookId));
//        String ISBN= book.getISBN();
        List<String> list = new ArrayList<>();
        list.add("9788962625936");
        AiRequest request = new AiRequest(list); //ai에 보낼 isbn리스트 생성 완료
        AiResponse aiResponse= recommendationFeignClient.findAiRecommend(request);//응답

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
    //삭제는 고민(사용자 등록책이 삭제되면 책도 삭제할것인가?)

}
