package com.lib.book;

import com.lib.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    // (메인화면) 책 조회
    //쿼리 성능 향상을 위해 추후 보완하기
    @Query(value = "SELECT * FROM book WHERE category_id= :categoryId AND customed = false ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Book> findRandomByCategory(@Param("categoryId") long categoryId);

    @Query(value = "SELECT * FROM book WHERE customed = false  AND officialRating >= 9.0 ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Book> findByBookRating();
}
