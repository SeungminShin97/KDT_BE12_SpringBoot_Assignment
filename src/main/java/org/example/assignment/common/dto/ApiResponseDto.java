package org.example.assignment.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    // 데이터
    private T data;
    // 상태 코드
    private int code;
    // 요청 성공 여부
    private Boolean success;
    // 클라이언트에게 보여줄 메세지
    private String message;
    // 응답 생성 시각
    private Instant timestamp;
}
