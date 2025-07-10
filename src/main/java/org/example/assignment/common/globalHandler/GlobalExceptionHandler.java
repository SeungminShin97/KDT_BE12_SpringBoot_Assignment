package org.example.assignment.common.globalHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 예외를 처리하는 전역 핸들러입니다. <br>
     * 귀찮아서 모든 예외는 예외 메세지를 출력 후 이전 페이지 혹은 홈페이지로 돌아갑니다.
     * @param request
     * @param ex
     * @param model
     * @return 이전 페이지 or 홈페이지
     */
    @ExceptionHandler(RuntimeException.class)
    protected String handleException(HttpServletRequest request, RuntimeException ex, Model model){
        // 이전 url 구하기
        String redirectUrl = request.getHeader("referer");
        // 이전 url이 없거나 같은 곳이면 홈페이지로 이동
        if (redirectUrl == null || redirectUrl.contains("/messageRedirect"))
            model.addAttribute("redirectUrl", "/");
        else
            model.addAttribute("redirectUrl", redirectUrl);

        model.addAttribute("message", ex.getMessage());

        return "messageRedirect";
    }
}
