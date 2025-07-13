package org.example.assignment.domain.user.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.assignment.domain.address.dto.AddressRequestDto;
import org.example.assignment.domain.enums.user.Gender;
import org.example.assignment.domain.user.User;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class UserRequestDto extends UserBaseDto {
    @Setter
    private String password;
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
