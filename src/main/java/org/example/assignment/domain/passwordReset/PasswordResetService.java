package org.example.assignment.domain.passwordReset;

import lombok.RequiredArgsConstructor;
import org.example.assignment.common.CodeGenerator;
import org.example.assignment.domain.enums.passwordReset.PasswordResetTokenStatus;
import org.example.assignment.domain.user.User;
import org.example.assignment.domain.user.UserRepository;
import org.example.assignment.domain.user.dto.UserBaseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final PasswordResetRepository passwordResetRepository;
    private final int PASSWORD_TOKEN_LENGTH = 8;
    private final int SESSION_ID_LENGTH = 32;

    /**
     * 비밀번호 재설정 토큰을 생성하고 반환하는 메서드입니다. <br>
     * @param user 토큰을 요청한 유저 정보
     * @return 새로 생성된 토큰
     */
    @Transactional
    public PasswordResetTokenDto createTokenAndReturn(User user) {
        // 토큰 생성
        String token = CodeGenerator.generateCode(PASSWORD_TOKEN_LENGTH);

        // 세션 Id 생성
        String sessionId = CodeGenerator.generateCode(SESSION_ID_LENGTH);

        // 토큰 엔티티 생성
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .user(user)
                .token(token)
                .sessionId(sessionId)
                .status(PasswordResetTokenStatus.ISSUED)
                .issuedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5)).build();

        // 토큰 저장
        passwordResetRepository.save(passwordResetToken);

        // 토큰 dto 반환
        PasswordResetTokenDto tokenDto = PasswordResetTokenDto.builder()
                .token(passwordResetToken.getToken())
                .sessionId(passwordResetToken.getSessionId())
                .status(passwordResetToken.getStatus())
                .issuedAt(passwordResetToken.getIssuedAt())
                .expiresAt(passwordResetToken.getExpiresAt()).build();
        tokenDto.setUserBaseDto(user.toUserBaseDto());
        return tokenDto;
    }
}
