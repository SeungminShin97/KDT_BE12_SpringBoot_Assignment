package org.example.assignment.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.assignment.domain.address.Address;
import org.example.assignment.domain.enums.user.Role;
import org.example.assignment.domain.user.dto.UserRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입 메서드 입니다. <br>
     * 사용자의 정보를 받아서 user, address, user_roles 엔티티를 저장합니다. <br>
     * 비밀번호 해싱, 유저 기본 권한(ROLE_USER)을 주입합니다.
     * @param userRequestDto 유저 정보
     */
    @Transactional
    public void createUser(UserRequestDto userRequestDto) {
        // 비밀번호 해싱
        String hashedPW = bCryptPasswordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(hashedPW);

        User user = userRequestDto.toEntity();
        // 유저 권한 주입, 회원가입 시에는 유저 권한만 가능
        user.addRole(Role.ROLE_USER);

        // 배송지 정보 저장
        if(userRequestDto.getAddress() != null) {
            Address address = userRequestDto.getAddress().toEntity(user);
            user.addAddress(address);
        }

        // 유저 저장
        userRepository.save(user);
    }

    /**
     * 이메일 중복 검사 메서드 입니다. <br>
     * @param email 중복 검사 할 email
     * @return 중복 되는 경우 true를 반환합니다.
     */
    public Boolean isEmailRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
}
