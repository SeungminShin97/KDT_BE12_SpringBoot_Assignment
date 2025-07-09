package org.example.assignment.domain.address.dto;

import lombok.*;
import org.example.assignment.domain.address.Address;
import org.example.assignment.domain.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressRequestDto {
    private String name;
    private String zipCode;
    private String roadAddress;
    private String detailAddress;

    /**
     * 엔티티로 변환하는 메서드입니다.
     * @param user 해당 주소의 유저 정보
     * @return Address Entity
     */
    public Address toEntity(User user) {
        return Address.builder()
                .user(user)
                .name(name)
                .zipCode(zipCode)
                .roadAddress(roadAddress)
                .detailAddress(detailAddress).build();
    }
}
