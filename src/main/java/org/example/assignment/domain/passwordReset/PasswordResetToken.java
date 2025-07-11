package org.example.assignment.domain.passwordReset;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.assignment.domain.user.User;
import org.example.assignment.domain.user.dto.UserRequestDto;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {
    @Id
    @GeneratedValue
    private Long id;

    // 토큰을 요청한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 토큰
    @Column(nullable = false)
    private String token;

    // 토큰 요청 시간
    @Column(nullable = false)
    private LocalDateTime issuedAt;

    // 토큰 만료 시간
    @Column(nullable = false)
    private LocalDateTime expiresAt =  LocalDateTime.now();

    public PasswordResetTokenDto toDto(UserRequestDto userRequestDto) {
        return PasswordResetTokenDto.builder()
                .userBaseDto(userRequestDto)
                .token(token)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .build();
    }

}
