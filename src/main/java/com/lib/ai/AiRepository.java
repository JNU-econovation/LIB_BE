package com.lib.ai;

import com.lib.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AiRepository extends JpaRepository<Book, Long> {

/*
    @Query(value = "SELECT * FROM book WHERE category_id= :categoryId AND customed = false ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Book> findAiRecommendDetail(@Param("categoryId") long categoryId);

 */

}
