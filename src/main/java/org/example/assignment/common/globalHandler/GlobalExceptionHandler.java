package org.example.assignment.common.globalHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.assignment.common.MessageRedirector;
import org.example.assignment.common.dto.MessageRedirectDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler implements MessageRedirector {

    /**
     * 예외를 처리하는 전역 핸들러입니다. <br>
     * 귀찮아서 모든 예외는 예외 메세지를 출력 후 이전 페이지 혹은 홈페이지로 돌아갑니다.
     * @param request
     * @param ex
     * @param model
     * @return 이전 페이지 or 홈페이지
     */
    @ExceptionHandler(RuntimeException.class)
    protected String handleException(HttpServletRequest request,
                                     RuntimeException ex,
                                     Model model) {
        MessageRedirectDto dto = MessageRedirectDto.builder()
                .redirectUrl(request.getHeader("referer"))
                .message(ex.getMessage())
                .build();

        return messageRedirect(request, model, dto);
    }
}
