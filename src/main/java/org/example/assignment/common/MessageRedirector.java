package org.example.assignment.common;

import jakarta.servlet.http.HttpServletRequest;
import org.example.assignment.common.dto.MessageRedirectDto;
import org.springframework.ui.Model;

/**
 * 클라이언트 사이드 메시지+리다이렉트 로직을 공통으로 처리하는 인터페이스
 */
public interface MessageRedirector {

    /**
     * 예외 발생 시 무한 루프를 방지하기 위해 리다이렉트 URL을 검증하고,
     * 클라이언트 사이드 리다이렉트용 뷰로 이동합니다.
     *
     * @param request HTTP 요청 객체 (Referer 헤더 검사용)
     * @param model   뷰에 전달할 모델
     * @param dto     메시지 및 리다이렉트 URL 정보
     * @return "messageRedirect" 뷰 이름
     */
    default String messageRedirect(HttpServletRequest request, Model model, MessageRedirectDto dto) {
        String target = dto.redirectUrl();
        String referer = request.getHeader("referer");

        // DTO에 지정된 URL이 메시지 뷰거나, referer가 메시지 뷰라면 홈("/")으로 보낸다
        if (target == null
                || target.contains("/messageRedirect")
                || (referer != null && referer.contains("/messageRedirect"))) {
            target = "/";
        }

        model.addAttribute("redirectUrl", target);
        model.addAttribute("message", dto.message());

        return "messageRedirect";
    }
}
