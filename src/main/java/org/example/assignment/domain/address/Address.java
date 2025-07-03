package org.example.assignment.domain.address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.assignment.domain.user.User;

@Entity
@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 주소 별칭
    @Column
    private String name;

    // 우편 번호
    @Column(nullable = false)
    private String zipCode;

    // 도로명 주소
    @Column(nullable = false)
    private String road_address;

    // 상세 주소
    @Column(nullable = false)
    private String detailAddress;
}
