package org.example.assignment.domain.user.dto;

import lombok.*;
import org.example.assignment.domain.address.dto.AddressRequestDto;
import org.example.assignment.domain.enums.user.Gender;
import org.example.assignment.domain.user.User;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRegistrationDto {
    private String email;
    private String password;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private String tel;
    private AddressRequestDto address;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .gender(gender)
                .birthDate(birthDate)
                .tel(tel).build();
    }
}
