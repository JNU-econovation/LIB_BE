package com.lib.book;

import com.lib.book.Book;
import com.lib.category.Category;
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
    List<Book> findRandomByCategory(@Param("categoryId") Integer categoryId);

    @Query(value = "SELECT * FROM book WHERE customed = false  AND rating_official >= 9.0 ORDER BY RAND() LIMIT 12", nativeQuery = true)
    List<Book> findByBookRating();

//    @Query("SELECT b from Book b where b.ISBN = :ISBN")
    Optional<Book> findByISBN(String ISBN);

//메인화면 AI책 받기
@Query(value = "SELECT DISTINCT b.ISBN FROM book b " +
        "JOIN ( " +
        "    SELECT book_id " +
        "    FROM record " +
        "    WHERE member_id = :memberId " +
        "    UNION " +
        "    SELECT book_id " +
        "    FROM bookshelf " +
        "    WHERE member_id = :memberId " +
        ") AS combined ON b.book_id = combined.book_id", nativeQuery = true)
List<String> findByMemberId(@Param("memberId")Long memberId);


    @Query(value = "SELECT b FROM Book b WHERE b.bookId= :bookId")
    Optional<Book> findAllBooks(@Param("bookId") Long bookId);
}
