package org.example.assignment.domain.auth;

import lombok.RequiredArgsConstructor;
import org.example.assignment.domain.user.User;
import org.example.assignment.domain.user.UserRepository;
import org.example.assignment.domain.user.exception.UserDisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * 로그인 메서드입니다. Spring Security를 이용해 "/login"으로 POST 요청을 보낼 시 자동으로 처리됩니다. <br>
     * 로그인 성공시 {@link org.example.assignment.config.handler.CustomLoginSuccessHandler} 참고 <br>
     * 로그인 실패시 {@link org.example.assignment.config.handler.CustomLoginFailureHandler} 참고 <br>
     * @param email the username identifying the user whose data is required.
     * @return 로그인 성공 : 유저 정보 / 로그인 실패 : 예외
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findUserByEmailAndIsDeletedFalse(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보가 없습니다."));
        if(user.getIsDeleted())
            throw new UsernameNotFoundException("회원 정보가 없습니다.");
        if(user.isEnabled())
            throw new UserDisabledException("비활성화 된 계정입니다. 문의 부탁드립니다.");

        // 로그인 성공 -> 마지막 로그인 시간 업데이트
        user.updateLastLogin();
        return user;
    }

}
