package org.example.assignment.domain.login;

import lombok.RequiredArgsConstructor;
import org.example.assignment.domain.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보가 없습니다."));

    }
}
