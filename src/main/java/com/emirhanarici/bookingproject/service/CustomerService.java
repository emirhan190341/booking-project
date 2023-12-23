package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.UserDTO;
import com.emirhanarici.bookingproject.exception.user.EmailAlreadyExistsException;
import com.emirhanarici.bookingproject.model.User;
import com.emirhanarici.bookingproject.model.enums.Role;
import com.emirhanarici.bookingproject.model.mapper.user.UserMapper;
import com.emirhanarici.bookingproject.payload.request.customer.CustomerCreateRequest;
import com.emirhanarici.bookingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public UserDTO createCustomer(CustomerCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_CUSTOMER)
                .build();

        return UserMapper.toDTO(userRepository.save(user));

    }


}
