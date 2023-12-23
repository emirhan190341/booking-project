package com.emirhanarici.bookingproject.model.mapper.order;

import com.emirhanarici.bookingproject.dto.OrderDTO;
import com.emirhanarici.bookingproject.model.Order;
import com.emirhanarici.bookingproject.model.mapper.user.UserMapper;
import com.emirhanarici.bookingproject.payload.response.CustomPageResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderCreatedResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderGetBetweenDatesResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderGetByCustomerResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderGetResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

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

    public static OrderGetResponse toGetResponse(OrderDTO source) {
        return OrderGetResponse.builder()
                .id(source.getId())
                .user(source.getUser())
                .orderItems(source.getOrderItems())
                .createdAt(source.getCreatedAt())
                .build();
    }

    public static OrderGetByCustomerResponse toGetByCustomerResponse(OrderDTO source) {
        return OrderGetByCustomerResponse.builder()
                .id(source.getId())
                .user(source.getUser())
                .orderItems(source.getOrderItems())
                .createdAt(source.getCreatedAt())
                .build();
    }


    public static CustomPageResponse<OrderGetByCustomerResponse> toGetByCustomerResponse(Page<OrderDTO> sources) {
        return CustomPageResponse.of(sources.map(OrderMapper::toGetByCustomerResponse));
    }

    public static OrderGetBetweenDatesResponse toGetBetweenDatesResponse(OrderDTO source) {
        return OrderGetBetweenDatesResponse.builder()
                .id(source.getId())
                .user(source.getUser())
                .orderItems(source.getOrderItems())
                .createdAt(source.getCreatedAt())
                .build();
    }

    public static CustomPageResponse<OrderGetBetweenDatesResponse> toGetBetweenDatesResponses(Page<OrderDTO> sources) {
        return CustomPageResponse.of(sources.map(OrderMapper::toGetBetweenDatesResponse));
    }



}
