package org.example.assignment.common;

import org.example.assignment.common.dto.MessageRedirectDto;
import org.springframework.ui.Model;

public interface MessageRedirector {
    default String messageRedirect(Model model, MessageRedirectDto messageRedirectDto) {
        model.addAttribute("message", messageRedirectDto.message());
        model.addAttribute("redirectUrl", messageRedirectDto.redirectUrl());
        return "messageRedirect";
    }
}
