package org.example.assignment.domain.auth;

import lombok.RequiredArgsConstructor;
import org.example.assignment.domain.enums.login.ErrorType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/login")
    public String goLogin(@RequestParam(value = "error", required = false) ErrorType error, Model model) {
        // 계정 비활성화 일 경우
        if(error == ErrorType.DISABLED)
            return "auth/disabled";

        // 로그인 실패 시
        if(error == ErrorType.BAD_CREDENTIALS)
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "auth/login";
    }
    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

}
