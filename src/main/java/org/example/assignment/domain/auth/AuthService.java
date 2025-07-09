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

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findUserByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보가 없습니다."));
        if(user.isDeleted())
            throw new UsernameNotFoundException("회원 정보가 없습니다.");
        if(user.isEnabled())
            throw new UserDisabledException("비활성화 된 계정입니다. 문의 부탁드립니다.");

        // 로그인 성공 -> 마지막 로그인 시간 업데이트
        user.updateLastLogin();
        return user;
    }
}
