package org.example.assignment.domain.passwordReset;

import lombok.Builder;
import lombok.Data;
import org.example.assignment.domain.enums.passwordReset.PasswordResetTokenStatus;
import org.example.assignment.domain.user.dto.UserBaseDto;
import java.time.LocalDateTime;

@Data
@Builder
public class PasswordResetTokenDto {
    private UserBaseDto userBaseDto;
    private String token;
    private String sessionId;
    private PasswordResetTokenStatus status;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
}
