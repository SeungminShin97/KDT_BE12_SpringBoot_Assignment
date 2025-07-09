package org.example.assignment.common.dto;

import lombok.Builder;
import org.springframework.ui.Model;

@Builder
public record MessageRedirectDto(
        String redirectUrl,
        String message
) {
}
