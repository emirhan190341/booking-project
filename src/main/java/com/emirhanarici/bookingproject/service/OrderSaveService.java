package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.OrderDTO;
import com.emirhanarici.bookingproject.dto.OrderItemDTO;
import com.emirhanarici.bookingproject.exception.user.UserNotFoundException;
import com.emirhanarici.bookingproject.model.Order;
import com.emirhanarici.bookingproject.model.User;
import com.emirhanarici.bookingproject.model.mapper.order.OrderItemMapper;
import com.emirhanarici.bookingproject.model.mapper.order.OrderMapper;
import com.emirhanarici.bookingproject.payload.request.order.CreateOrderRequest;
import com.emirhanarici.bookingproject.repository.OrderRepository;
import com.emirhanarici.bookingproject.security.CustomUserDetails;
import com.emirhanarici.bookingproject.util.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSaveService {

    private final OrderItemService orderItemService;

    private final UserService userService;

    private final OrderRepository orderRepository;

    private final Identity identity;



    @Transactional
    public OrderDTO createOrder(CreateOrderRequest createOrderRequest) {

        CustomUserDetails customUserDetails = identity.getCustomUserDetails();

        User user = userService.findByEmail(customUserDetails.getEmail())
                .orElseThrow(() -> new UserNotFoundException(customUserDetails.getId()));

        List<OrderItemDTO> orderItemDTOs = createOrderRequest
                .getOrderDetailSet()
                .stream()
                .map(orderItemService::createOrderItem)
                .toList();

        Order order = Order.builder()
                .user(user)
                .build();

        order.setOrderItems(OrderItemMapper.toOrderItem(orderItemDTOs));

        return OrderMapper.toOrderDTO(orderRepository.save(order));

    }




}
