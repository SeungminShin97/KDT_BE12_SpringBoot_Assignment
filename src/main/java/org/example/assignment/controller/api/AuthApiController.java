package org.example.assignment.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.assignment.common.dto.ApiResponseDto;
import org.example.assignment.domain.passwordReset.PasswordResetOrchestrator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {
    private final PasswordResetOrchestrator passwordResetOrchestrator;

    /**
     * 지정된 이메일 주소로 비밀번호 재설정 메일 발송 흐름을 시작합니다.
     * <p>
     * 이 엔드포인트는 다음 절차를 수행합니다:
     * <ol>
     *   <li>이메일이 등록된 사용자 계정인지 확인합니다.</li>
     *   <li>해당 사용자에게 발급된 기존 비밀번호 재설정 토큰을 만료 처리합니다.</li>
     *   <li>새로운 암호학적으로 안전한 재설정 토큰을 생성합니다.</li>
     *   <li>생성된 토큰을 포함한 비밀번호 재설정 메일을 해당 이메일로 발송합니다.</li>
     * </ol>
     *
     * @param email 비밀번호 재설정을 요청하는 사용자의 이메일 주소
     * @return {@link ApiResponseDto} 객체:
     *         <ul>
     *           <li>{@code data}: 메일 발송 요청이 정상 처리되었으면 {@code true}</li>
     *           <li>{@code success}: 서비스 로직이 예외 없이 완료되었으면 {@code true}</li>
     *           <li>{@code code}: HTTP 상태 코드 (200)</li>
     *           <li>{@code timestamp}: 응답이 생성된 시각</li>
     *         </ul>
     * @throws IllegalArgumentException 이메일이 등록된 사용자가 아닐 경우
     * @throws RuntimeException    토큰 생성 또는 메일 발송 중 예기치 못한 오류 발생 시
     * By GPT Sensei
     */
    @PostMapping("/password-reset")
    public ApiResponseDto<Boolean> sendPasswordResetEmail(@RequestParam String email){
        // 이메일 처리 로직
        passwordResetOrchestrator.sendPasswordResetEmail(email);
        return ApiResponseDto.<Boolean>builder()
                .data(true)
                .success(true)
                .code(HttpStatus.OK.value())
                .timestamp(Instant.now())
                .build();
    }

}
