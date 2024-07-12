package com.lib.category;

import com.lib.BaseEntity;
import com.lib.member.Member;
import com.lib.book.Book;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    public static final String Prefix="category";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=Prefix+"_id", nullable = false)
    private Long categoryId;

    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)//categoryId에서 참조하며 부모엔티티 상태변화가 자식엔티티에게 전파됨
    private List<Member> members=new ArrayList<>();

    @OneToMany(mappedBy = Prefix, cascade = CascadeType.MERGE)//categoryId에서 참조하며 부모엔티티 상태변화가 자식엔티티에게 전파됨
    private List<Book> books=new ArrayList<>();

    @Column(name=Prefix+"_type", nullable = false)
    private String categoryType;

}
