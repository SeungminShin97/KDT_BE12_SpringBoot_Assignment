package org.example.assignment.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.assignment.domain.user.exception.UserDisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final RedirectStrategy redirect = new DefaultRedirectStrategy();

    /**
     * 로그인 실패시 처리 핸들러 입니다. <br>
     * 로그인 페이지로 리다이렉트 하며 예외 정보를 쿼리 파라미터로 넘깁니다.
     * @param request the request during which the authentication attempt occurred.
     * @param response the response.
     * @param exception the exception which was thrown to reject the authentication
     * request.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof UserDisabledException)
            redirect.sendRedirect(request,response,"/login?error=DISABLED");
        else
            redirect.sendRedirect(request,response,"/login?error=BAD_CREDENTIALS");
    }
}
