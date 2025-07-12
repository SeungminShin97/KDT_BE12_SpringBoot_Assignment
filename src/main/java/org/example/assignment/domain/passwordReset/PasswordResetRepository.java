package org.example.assignment.domain.passwordReset;

import org.example.assignment.domain.enums.passwordReset.PasswordResetTokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    List<PasswordResetToken> findByUserIdAndStatus(Long userId, PasswordResetTokenStatus status);
}
