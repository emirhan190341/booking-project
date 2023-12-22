package com.emirhanarici.bookingproject.controller;

import com.emirhanarici.bookingproject.dto.OrderDTO;
import com.emirhanarici.bookingproject.model.mapper.order.OrderMapper;
import com.emirhanarici.bookingproject.payload.request.order.CreateOrderRequest;
import com.emirhanarici.bookingproject.payload.response.CustomResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderCreatedResponse;
import com.emirhanarici.bookingproject.service.OrderSaveService;
import com.emirhanarici.bookingproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderSaveService orderSaveService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<OrderCreatedResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        final OrderDTO orderDTO = orderSaveService.createOrder(createOrderRequest);
        final OrderCreatedResponse response = OrderMapper.toCreatedResponse(orderDTO);
        return CustomResponse.created(response);
    }

}
