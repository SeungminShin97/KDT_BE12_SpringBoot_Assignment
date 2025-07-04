package org.example.assignment.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.assignment.domain.address.Address;
import org.example.assignment.domain.enums.Gender;
import org.example.assignment.domain.enums.Grade;
import org.example.assignment.domain.enums.Role;
import org.example.assignment.common.BaseTimeEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private boolean isDeleted;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    // 전화번호
    @Column
    private String tel;

    // 유저 배송지 정보
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Address>  addresses;

    // 기본 배송지
    @OneToOne
    @JoinColumn(name = "base_address_id")
    private Address baseAddress;

    // 최근 배송지
    @OneToOne
    @JoinColumn(name = "last_used_address")
    private Address lastUsedAddress;

    // 유저 등급
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.BRONZE;

    // 계정 활성화 여부
    @Column(nullable = false)
    private boolean isEnabled;

    // 유저 권한
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(nullable = false)
    private Set<Role> role = new HashSet<>();

    // 마지막 로그인 시간
    @Column
    private LocalDateTime lastLogin;

    // 회원 탈퇴 시간
    @Column
    private LocalDateTime deletedAt;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * 계정 활성화 여부 <br>
     * true : 활성화 계정 <br>
     * false : 비활성화 계정
     * @return 활성화 여부
     */
    @Override
    public boolean isEnabled() {
        return this.isEnabled && !this.isDeleted;
    }
}
