package com.lib.category;

import com.lib.book.Book;
import com.lib.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCatetoryType(@Param("categoryType") String categoryType);

    //쿼리 성능 향상을 위해 추후 보완하기
    @Query(value = "SELECT category_id FROM category WHERE category_type= :categoryType", nativeQuery = true)
    Integer findIdByCategory(@Param("categoryType") String categoryType);
}
