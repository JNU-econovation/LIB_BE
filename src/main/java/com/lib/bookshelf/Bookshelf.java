package com.lib.bookshelf;

import com.lib.BaseEntity;
import com.lib.member.Member;
import com.lib.book.Book;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookshelf extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookshelf_id", updatable = false)
    private Long bookshelfId;

   //회원, 책 아이디 fk
   @ManyToOne
   @JoinColumn(name = "member_id", nullable = false)
   private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
