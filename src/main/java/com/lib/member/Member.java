package com.lib.member;

import com.lib.BaseEntity;
import com.lib.record.Record;
import com.lib.bookshelf.Bookshelf;
import com.lib.category.Category;
import com.lib.comment.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    public static final String Prefix="member";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//AUTO_INCREMENT
    @Column(name=Prefix+"_id", updatable = false)
    private Long id;

    //참조하는 엔티티의 필드에 매핑
    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)
    List<Record> records=new ArrayList<>();

    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)
    List<Comment> comments=new ArrayList<>();

    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)
    List<Bookshelf> bookshelves=new ArrayList<>();


    @Column(name=Prefix+"_loginid", nullable = false)
    private String memberLoginId;

    @Column(name = "pw", nullable = false)
    private String pw;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)//확인
    private Category category;

    @Column(name="reset_cycle", nullable = false)
    private Integer resetCycle;

    @Column(name="goal_number", nullable = false)
    private Integer goalNumber;

    @Column(nullable = false)
    private String nickname;



}
