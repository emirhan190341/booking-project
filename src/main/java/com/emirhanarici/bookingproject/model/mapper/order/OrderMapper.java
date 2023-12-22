package com.emirhanarici.bookingproject.model.mapper.order;

import com.emirhanarici.bookingproject.dto.OrderDTO;
import com.emirhanarici.bookingproject.model.Order;
import com.emirhanarici.bookingproject.model.mapper.user.UserMapper;
import com.emirhanarici.bookingproject.payload.response.order.OrderCreatedResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {

    public static OrderDTO toOrderDTO(Order source) {
        return OrderDTO.builder()
                .id(source.getId())
                .user(UserMapper.toDTO(source.getUser()))
                .orderItems(OrderItemMapper.toDTO(source.getOrderItems()))
                .createdAt(source.getCreatedAt())
                .build();
    }


    public static OrderCreatedResponse toCreatedResponse(OrderDTO orderDTO) {

        return OrderCreatedResponse.builder()
                .id(orderDTO.getId())
                .orderItems(orderDTO.getOrderItems())
                .user(orderDTO.getUser())
                .createdAt(orderDTO.getCreatedAt())
                .build();
    }






}
