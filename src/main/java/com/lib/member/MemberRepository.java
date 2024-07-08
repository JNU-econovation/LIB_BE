package com.lib.member;

import com.lib.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberLoginId(@Param("loginId") String loginId);
}