package com.lib.bookshelf;

import com.lib.BaseEntity;
import com.lib.member.Member;
import com.lib.book.Book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public Bookshelf(Long bookshelfId, Member member, Book book) {
        this.bookshelfId = bookshelfId;
        this.member = member;
        this.book = book;
    }
}
