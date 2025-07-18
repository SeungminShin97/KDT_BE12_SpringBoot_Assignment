package org.example.assignment.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.assignment.common.dto.ApiResponseDto;
import org.example.assignment.common.exception.DatabaseException;
import org.example.assignment.domain.user.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    /**
     * 이메일 중복 검사
     * @param email 확인할 이메일
     * @return data: true(사용가능), false(중복)
     */
    @GetMapping("/email-exists")
    public ApiResponseDto<Boolean> isEmailAvailable(@RequestParam String email){
        try{
            Boolean isAvailable = userService.isEmailRegistered(email);
            return ApiResponseDto.<Boolean>builder()
                    .data(isAvailable)
                    .code(HttpStatus.OK.value())
                    .success(true)
                    .message((isAvailable) ? "사용가능한 이메일 입니다." : "중복된 이메일 입니다.")
                    .timestamp(Instant.now()).build();
        } catch (DataAccessException e) {
            throw new DatabaseException("이메일 중복 검사 중 오류가 발생했습니다");
        }
    }
}
