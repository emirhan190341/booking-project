package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.OrderReportDTO;
import com.emirhanarici.bookingproject.model.enums.Role;
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
public class StatisticsService {

    private final OrderRepository orderRepository;

    private final Identity identity;

    public Page<OrderReportDTO> getOrderStatisticsByCustomerId(Long customerId, PaginationRequest paginationRequest) {

        final CustomUserDetails userDetails = identity.getCustomUserDetails();
        final Role userRole = userDetails.getUser().getRole();
        if ((userRole.equals(Role.ROLE_CUSTOMER) && userDetails.getId().equals(customerId))
                || userRole.equals(Role.ROLE_ADMIN)) {
            return orderRepository.findOrderStatisticsByCustomerId(customerId, paginationRequest.toPageable());
        }
        throw new AccessDeniedException("You cannot access order statistics");
    }


    public Page<OrderReportDTO> getAllOrderStatistics(PaginationRequest paginationRequest) {
        return orderRepository.findAllOrderStatistics(paginationRequest.toPageable());
    }

}
