package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.OrderDTO;
import com.emirhanarici.bookingproject.exception.order.OrderNotFoundException;
import com.emirhanarici.bookingproject.model.enums.Role;
import com.emirhanarici.bookingproject.model.mapper.order.OrderMapper;
import com.emirhanarici.bookingproject.payload.request.pagination.DateIntervalRequest;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginatedFindAllRequest;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginationRequest;
import com.emirhanarici.bookingproject.repository.OrderRepository;
import com.emirhanarici.bookingproject.security.CustomUserDetails;
import com.emirhanarici.bookingproject.util.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final Identity identity;


    public OrderDTO findOrderById(Long id) {

        CustomUserDetails userDetails = identity.getCustomUserDetails();

        return orderRepository.findById(id)
                .map(order -> {
                    // Check access based on customUserDetails here
                    if ((userDetails.getId().equals(order.getUser().getId()) &&
                            userDetails.getUser().getRole().equals(Role.ROLE_CUSTOMER))
                            || userDetails.getUser().getRole().equals(Role.ROLE_ADMIN)) {
                        return OrderMapper.toOrderDTO(order);
                    } else {
                        throw new AccessDeniedException("You cannot access this order by Id");
                    }
                })
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }


    public Page<OrderDTO> findAllOrdersByCustomerId(Long customerId, PaginationRequest paginationRequest) {

        final CustomUserDetails userDetails = identity.getCustomUserDetails();
        final Role userRole = userDetails.getUser().getRole();
        if ((userRole.equals(Role.ROLE_CUSTOMER) && userDetails.getId().equals(customerId))
                || userRole.equals(Role.ROLE_ADMIN)) {
            return orderRepository.findAllByUserId(customerId, paginationRequest.toPageable())
                    .map(OrderMapper::toOrderDTO);
        }

        throw new AccessDeniedException("You cannot access order statistics");

    }


    public Page<OrderDTO> findAllOrdersBetweenTwoDatesAndPagination(PaginatedFindAllRequest paginatedFindAllRequest) {

        DateIntervalRequest dateIntervalRequest = paginatedFindAllRequest.getDateIntervalRequest();
        PaginationRequest paginationRequest = paginatedFindAllRequest.getPaginationRequest();

        return orderRepository.findAllByCreatedAtBetween(
                        dateIntervalRequest.getStartDate(),
                        dateIntervalRequest.getEndDate(),
                        paginationRequest.toPageable())
                .map(OrderMapper::toOrderDTO);

    }
}
