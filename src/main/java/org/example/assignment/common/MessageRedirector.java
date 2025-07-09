package org.example.assignment.common;

import org.example.assignment.common.dto.MessageRedirectDto;
import org.springframework.ui.Model;

public interface MessageRedirector {
    default String messageRedirect(Model model, MessageRedirectDto messageRedirectDto) {
        model.addAttribute("message", messageRedirectDto.message());
        return messageRedirectDto.redirectUrl();
    }
}
