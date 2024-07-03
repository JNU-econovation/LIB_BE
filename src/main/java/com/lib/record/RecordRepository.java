package com.lib.record;

import com.lib.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    //나의기록 페이지 전체 기록 조회
    @Query("SELECT r FROM Record r WHERE r.member.id =:memberId")
    List<Record> findRecordByMemberId(Long memberId);

}
