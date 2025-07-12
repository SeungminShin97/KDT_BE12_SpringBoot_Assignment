package org.example.assignment.domain.passwordReset;

import lombok.RequiredArgsConstructor;
import org.example.assignment.domain.mail.MailService;
import org.example.assignment.domain.user.User;
import org.example.assignment.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PasswordResetOrchestrator {
    private final UserService userService;
    private final PasswordResetService passwordResetService;
    private final MailService mailService;

    @Transactional
    public void sendPasswordResetEmail(String email){
        // 이메일 검증
        if(!userService.isEmailRegistered(email))
            throw new NoSuchElementException("등록되지 않은 이메일입니다.");

        // 유저 조회
        User user = userService.getUserByEmail(email);

        // 비밀번호 인증 토큰 생성
        PasswordResetTokenDto dto = passwordResetService.createTokenAndReturn(user);

        // 메일 전송
        mailService.passwordResetMailSend(dto);
    }
}
