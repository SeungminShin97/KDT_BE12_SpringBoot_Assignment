package org.example.assignment.domain.passwordReset;

import lombok.RequiredArgsConstructor;
import org.example.assignment.common.CodeGenerator;
import org.example.assignment.domain.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final PasswordResetRepository passwordResetRepository;
    private final int PASSWORD_TOKEN_LENGTH = 8;

    @Transactional
    public PasswordResetTokenDto createTokenAndReturn(User user) {
        // 토큰 생성
        String token = CodeGenerator.generateCode(PASSWORD_TOKEN_LENGTH);

        // 토큰 엔티티 생성
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .user(user)
                .token(token)
                .issuedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5)).build();

        // 토큰 저장
        passwordResetRepository.save(passwordResetToken);

        // 토큰 dto 반환
        PasswordResetTokenDto tokenDto = PasswordResetTokenDto.builder()
                .token(passwordResetToken.getToken())
                .issuedAt(passwordResetToken.getIssuedAt())
                .expiresAt(passwordResetToken.getExpiresAt()).build();
        tokenDto.setUserBaseDto(user.toUserBaseDto());
        return tokenDto;
    }
}
