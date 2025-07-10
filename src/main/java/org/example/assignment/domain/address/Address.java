package org.example.assignment.domain.address;

import jakarta.persistence.*;
import lombok.*;
import org.example.assignment.domain.user.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 기본 배송지 여부
    @Column
    private Boolean isBase = false;

    // 최근 배송지 여부
    @Column
    private Boolean isLast = false;

    // 주소 별칭
    @Column
    private String name;

    // 우편 번호
    @Column(nullable = false)
    private String zipCode;

    // 도로명 주소
    @Column(nullable = false)
    private String roadAddress;

    // 상세 주소
    @Column(nullable = false)
    private String detailAddress;

    public void markAsBase() {
        isBase = true;
    }

    public void unmarkAsBase() {
        isBase = false;
    }

    public void markAsLast() {
        isLast = true;
    }

    public void unmarkAsLast() {
        isLast = false;
    }

}
