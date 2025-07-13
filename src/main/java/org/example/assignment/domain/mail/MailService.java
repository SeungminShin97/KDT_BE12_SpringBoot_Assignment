package org.example.assignment.domain.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.assignment.domain.passwordReset.PasswordResetTokenDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;

    public void passwordResetMailSend(PasswordResetTokenDto passwordResetTokenDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 수신자
        mailMessage.setTo(passwordResetTokenDto.getUserBaseDto().getEmail());
        // 메일의 제목
        mailMessage.setSubject("비밀번호 재설정 인증코드");
        // 메일의 내용
        mailMessage.setText(
                "인증번호는 \n"
                + passwordResetTokenDto.getToken() + "\n"
                + "입니다. \n"
                + "접속 링크 : " + "localhost:8080/auth/password?sessionId=" + passwordResetTokenDto.getSessionId()

        );
        mailSender.send(mailMessage);
        log.info("send message to : " + passwordResetTokenDto.getUserBaseDto().getEmail());
        log.info("send message at " +  passwordResetTokenDto.getIssuedAt());
    }
}
