package org.example.assignment.domain.auth;

import lombok.RequiredArgsConstructor;
import org.example.assignment.common.MessageRedirector;
import org.example.assignment.common.dto.MessageRedirectDto;
import org.example.assignment.domain.enums.login.ErrorType;
import org.example.assignment.domain.user.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController implements MessageRedirector {
    private final AuthService authService;

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

    //TODO: 예외처리
    /**
     * 회원가입 처리 메서드입니다. <br>
     * 회원가입 성공 시 홈페이지를 반환합니다.
     * @param registrationDto 회원정보
     * @return 홈페이지 반환
     */
    @PostMapping("/register")
    public String processRegistration(UserRegistrationDto registrationDto, Model model){
        authService.register(registrationDto);
        return messageRedirect(model,new MessageRedirectDto("/", "회원가입 되었습니다."));
    }

}
