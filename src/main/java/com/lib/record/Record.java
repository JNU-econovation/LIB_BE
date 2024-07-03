package com.lib.record;

import com.lib.BaseEntity;
import com.lib.book.Book;
import com.lib.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {
    public static final String Prefix="record";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =Prefix+ "_id", updatable = false)
    private Long recordId;

    //회원, 책 아이디 fk
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name =Prefix+ "_content", nullable = false, columnDefinition = "TEXT")
    private String recordContent;

    @Column(nullable = false)
    private Integer rating;

   @Builder
    public Record(Long recordId,Member member, Book book, String recordContent, Integer rating) {
       this.recordId=recordId;
       this.book=book;
        this.member=member;
        this.recordContent=recordContent;
        this.rating=rating;
    }

}

