package org.example.assignment.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.assignment.common.MessageRedirector;
import org.example.assignment.common.dto.MessageRedirectDto;
import org.example.assignment.domain.enums.login.ErrorType;
import org.example.assignment.domain.user.UserService;
import org.example.assignment.domain.user.dto.UserRequestDto;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements MessageRedirector {
    private final UserService  userService;

    /**
     * 로그인 페이지를 반환하는 API입니다.
     * @param error 로그인 오류 타입
     * @return 로그인 페이지
     */
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) ErrorType error, Model model) {
        // 계정 비활성화 일 경우
        if(error == ErrorType.DISABLED)
            return "auth/disabled";

        // 로그인 실패 시
        if(error == ErrorType.BAD_CREDENTIALS)
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "auth/login";
    }

    /**
     * 회원가입 페이지를 반환하는 API입니다.
     * @return 회원가입 페이지
     */
    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    /**
     * 회원가입 처리 메서드입니다. <br>
     * 회원가입 성공 시 홈페이지를 반환합니다. <br>
     * 회원가입 실패 시 에러 메세지 출력 후 회원가입 페이지로 리다이렉트 합니다.
     * @param userRequestDto 회원정보
     * @return 홈페이지 반환
     */
    @PostMapping("/register")
    public String processRegistration(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request, Model model){
        try {
            userService.createUser(userRequestDto);
        } catch (DataAccessException e) {
            return messageRedirect(request, model, MessageRedirectDto.builder().
                    redirectUrl("/register").
                    message("회원가입에 실패했습니다. 다시 시도해주세요").build());
        }
        return messageRedirect(request, model,MessageRedirectDto.builder()
                .redirectUrl("/")
                .message("회원가입 되었습니다.").build());
    }

    /**
     * 비밀번호 찾기 페이지로 이동합니다. <br>
     */
    @GetMapping("/password")
    public String showFindPasswordFrom() {
        return "auth/findPassword";
    }

    /**
     * 비밀번호 찾기 페이지로 이동합니다. <br>
     * 세션 Id를 가지고 있으면 바로 인증 코드를 입력할 수 있습니다. <br>
     * {@link org.example.assignment.domain.mail.MailService} passwordResetMailSend 메서드 참고
     * @param sessionId 비밀번호 인증 URL
     * @return
     */
    @GetMapping("/password")
    public String showFindPasswordFromWithSessionId(@RequestParam(value = "sessionId") String sessionId, Model model) {
        // sessionId로 인증 토큰 가져오기

        // model에 토큰 추가

        return "auth/findPassword";
    }
}
