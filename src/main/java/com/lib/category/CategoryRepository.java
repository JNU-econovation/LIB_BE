package com.lib.category;

import com.lib.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCatetoryType(@Param("categoryType") String categoryType);

}
