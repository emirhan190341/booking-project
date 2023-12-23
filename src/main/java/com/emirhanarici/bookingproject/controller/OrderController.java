package com.emirhanarici.bookingproject.controller;

import com.emirhanarici.bookingproject.dto.OrderDTO;
import com.emirhanarici.bookingproject.model.mapper.order.OrderMapper;
import com.emirhanarici.bookingproject.payload.request.order.CreateOrderRequest;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginatedFindAllRequest;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginationRequest;
import com.emirhanarici.bookingproject.payload.response.CustomPageResponse;
import com.emirhanarici.bookingproject.payload.response.CustomResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderCreatedResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderGetBetweenDatesResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderGetByCustomerResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderGetResponse;
import com.emirhanarici.bookingproject.service.OrderSaveService;
import com.emirhanarici.bookingproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{orderId}")
    public CustomResponse<OrderGetResponse> getOrderById(@PathVariable final Long orderId) {

        final OrderDTO orderDTO = orderService.findOrderById(orderId);
        final OrderGetResponse response = OrderMapper.toGetResponse(orderDTO);

        return CustomResponse.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public CustomResponse<CustomPageResponse<OrderGetByCustomerResponse>> getOrdersByCustomerId(
            @PathVariable Long customerId,
            @RequestBody PaginationRequest paginationRequest
    ) {

        final Page<OrderDTO> pageOfOrderDTOs = orderService
                .findAllOrdersByCustomerId(customerId, paginationRequest);

        final CustomPageResponse<OrderGetByCustomerResponse> response = OrderMapper
                .toGetByCustomerResponse(pageOfOrderDTOs);
        return CustomResponse.ok(response);

    }

    @PostMapping("/between-dates")
    public CustomResponse<CustomPageResponse<OrderGetBetweenDatesResponse>> getOrdersBetweenTwoDates(
            @RequestBody PaginatedFindAllRequest paginatedFindAllRequest
    ) {
        final Page<OrderDTO> pageOfOrderDTOs = orderService
                .findAllOrdersBetweenTwoDatesAndPagination(paginatedFindAllRequest);
        final CustomPageResponse<OrderGetBetweenDatesResponse> response = OrderMapper
                .toGetBetweenDatesResponses(pageOfOrderDTOs);

        return CustomResponse.ok(response);
    }

}
