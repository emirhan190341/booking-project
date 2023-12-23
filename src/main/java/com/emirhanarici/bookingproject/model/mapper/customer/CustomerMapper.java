package com.emirhanarici.bookingproject.model.mapper.customer;

import com.emirhanarici.bookingproject.dto.UserDTO;
import com.emirhanarici.bookingproject.payload.response.customer.CustomerCreatedResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMapper {
    public static CustomerCreatedResponse toCreatedResponse(UserDTO source) {

        if (source == null) {
            return null;
        }

        return CustomerCreatedResponse.builder()
                .id(source.getId())
                .fullName(source.getFullName())
                .username(source.getUsername())
                .email(source.getEmail())
                .build();
    }
}
