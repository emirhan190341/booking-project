package com.emirhanarici.bookingproject.payload.response.order;

import com.emirhanarici.bookingproject.dto.OrderItemDTO;
import com.emirhanarici.bookingproject.dto.UserDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderGetByCustomerResponse {

    private Long id;
    private UserDTO user;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> orderItems;
}
