package org.example.assignment.domain.book;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.assignment.common.BaseTimeEntity;
import org.example.assignment.domain.category.Category;
import org.xml.sax.ext.Locator2;

@Entity
@NoArgsConstructor
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 13)
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 50)
    private String subTitle;

    // 저자
    @Column(nullable = false, length = 40)
    private String author;

    // 출판사
    @Column(nullable = false, length = 30)
    private String publisher;

    // 책 출판일
    @Column(nullable = false)
    private String publishedDate;

    // 옮긴이
    @Column(length = 10)
    private String translator;

    // 상세 설명
    @Column(columnDefinition = "TEXT")
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
    @Column(columnDefinition = "TEXT")
    private String tableOfContents;

    // 재고
    @Column
    private Integer stockCnt = 0;
}
