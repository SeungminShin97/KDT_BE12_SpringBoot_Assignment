package org.example.assignment.domain.book;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.assignment.common.BaseTimeEntity;
import org.example.assignment.domain.category.Category;

@Entity
@NoArgsConstructor
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column
    private String subTitle;

    // 저자
    @Column(nullable = false)
    private String author;

    // 출판사
    @Column(nullable = false)
    private String publisher;

    // 책 출판일
    @Column(nullable = false)
    private String publishedDate;

    // 옮긴이
    @Column
    private String translator;

    // 상세 설명
    @Column
    private String info;

    // 가격
    @Column
    private Integer price;

    // 책 사이즈, ??x?? 형식
    @Column
    private String size;

    // 총 페이지 수
    @Column
    private Integer totalPage;

    // 책 무게
    @Column
    private String weight;

    // 목차
    @Column
    private String index;

    // 재고
    @Column
    private Integer stockCnt = 0;
}
