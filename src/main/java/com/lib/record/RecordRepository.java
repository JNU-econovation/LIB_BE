package com.lib.record;

import com.lib.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Book, Long> {

}
