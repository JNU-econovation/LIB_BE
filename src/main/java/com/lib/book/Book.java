package com.lib.book;

import com.lib.*;
import com.lib.category.Category;
import com.lib.record.Record;
import com.lib.bookshelf.Bookshelf;
import com.lib.comment.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

    public static final String Prefix="book";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=Prefix+"_id", nullable = false)
    private Long bookId;

    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)
    List<Record> records=new ArrayList<>();

    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)
    List<Comment> comments=new ArrayList<>();

    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)
    List<Bookshelf> bookshelves=new ArrayList<>();


    @Column(nullable = false)//
    private boolean customed;

    @Column(nullable = false)// 서재 추가 여부
    private boolean isAdded;

    @Column(nullable = true)
    private String ISBN;

    @Column(name=Prefix+"_name", nullable = false)
    private String bookName;

    @Column(name=Prefix+"_cover", nullable = true)
    private String bookCover;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name=Prefix+"_explain",nullable = true, columnDefinition = "TEXT")
    private String bookExplain;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "rating_official",nullable = true)
    private Integer ratingOfficial;

    @Column(nullable = false)
    private String publisher;





    @Builder
    public Book(Boolean customed, Boolean isAdded,String ISBN, String bookName, String bookCover, String author, Category category, String publisher,String bookExplain, Integer rating, Integer ratingOfficial){
        this.customed=customed;
        this.isAdded=isAdded;
        this.ISBN=ISBN;
        this.bookName=bookName;
        this.bookCover=bookCover;
        this.author=author;
        this.category=category;
        this.publisher=publisher;
        this.bookExplain=bookExplain;
        this.rating=rating;
        this.ratingOfficial=ratingOfficial;
    }




}
