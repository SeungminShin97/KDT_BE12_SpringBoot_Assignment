package org.example.assignment.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.assignment.common.dto.ApiResponseDto;
import org.example.assignment.common.exception.DatabaseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/email")
    @ResponseBody
    public ApiResponseDto<Boolean> duplicateEmail(@RequestParam String email){
        try{
            Boolean isAvailable = userService.duplicateEmail(email);
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
