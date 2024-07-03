package com.lib.comment;

import com.lib.BaseEntity;
import com.lib.member.Member;
import com.lib.book.Book;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    public static final String Prefix="comment";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Prefix+"_id", updatable = false)
    private Long commentId;
    //회원, 책 아이디 fk
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @Column(name = Prefix+"_context", nullable = false, columnDefinition = "TEXT")
    private String commentContent;
}
