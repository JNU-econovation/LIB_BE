package com.lib.book;

import com.lib.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

public interface BookRepository extends JpaRepository<Book, Long> {


    // (메인화면) 책 조회
    //쿼리 성능 향상을 위해 추후 보완하기
    @Query(value = "SELECT * FROM book WHERE category_id= :categoryId AND customed = false ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Book> findRandomByCategory(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT * FROM book WHERE customed = false  AND rating_official >= 9.0 ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Book> findByBookRating();

//    @Query("SELECT b from Book b where b.ISBN = :ISBN")
    Optional<Book> findByISBN(String ISBN);

//메인화면 AI책 받기
    @Query(value = "SELECT b.ISBN FROM book b " +
            "JOIN record r ON b.book_id = r.book_id " +
            "JOIN bookshelf bs ON b.book_id = bs.book_id " +
            "WHERE r.member_id = :memberId OR bs.member_id = :memberId", nativeQuery = true)
    List<String> findByMemberId(@Param("memberId")Long memberId);


    @Query(value = "SELECT b FROM Book b WHERE b.bookId= :bookId")
    Optional<Book> findAllBooks(Long bookId);
}
