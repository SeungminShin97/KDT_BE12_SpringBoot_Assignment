package org.example.assignment.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.assignment.domain.address.Address;
import org.example.assignment.domain.enums.user.Role;
import org.example.assignment.domain.user.dto.UserRegistrationDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public void register(UserRegistrationDto userRegistrationDto) {
        // 비밀번호 해싱
        String hashedPW = bCryptPasswordEncoder.encode(userRegistrationDto.getPassword());
        userRegistrationDto.setPassword(hashedPW);

        User user = userRegistrationDto.toEntity();
        // 유저 권한 주입, 회원가입 시에는 유저 권한만 가능
        user.addRole(Role.ROLE_USER);

        // 배송지 정보 저장
        if(userRegistrationDto.getAddress() != null) {
            Address address = userRegistrationDto.getAddress().toEntity(user);
            user.addAddress(address);
        }

        // 유저 저장
        userRepository.save(user);
    }
}
