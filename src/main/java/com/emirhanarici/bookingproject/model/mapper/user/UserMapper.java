package com.emirhanarici.bookingproject.model.mapper.user;

import com.emirhanarici.bookingproject.dto.UserDTO;
import com.emirhanarici.bookingproject.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
