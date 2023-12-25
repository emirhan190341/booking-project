package com.emirhanarici.bookingproject.controller;

import com.emirhanarici.bookingproject.dto.OrderReportDTO;
import com.emirhanarici.bookingproject.model.mapper.order.OrderReportMapper;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginationRequest;
import com.emirhanarici.bookingproject.payload.response.CustomPageResponse;
import com.emirhanarici.bookingproject.payload.response.CustomResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderReportResponse;
import com.emirhanarici.bookingproject.service.StatisticsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_CUSTOMER')")
    public CustomResponse<CustomPageResponse<OrderReportResponse>> getOrderStatisticsByCustomerId(
            @PathVariable Long customerId,
            @RequestBody PaginationRequest paginationRequest
    ) {
        Page<OrderReportDTO> orderReportDTOs = statisticsService
                .getOrderStatisticsByCustomerId(customerId, paginationRequest);
        CustomPageResponse<OrderReportResponse> orderReportResponse = OrderReportMapper.toOrderReportResponseList(orderReportDTOs);
        return CustomResponse.ok(orderReportResponse);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CustomResponse<CustomPageResponse<OrderReportResponse>> getAllOrderStatistics(
            @RequestBody PaginationRequest paginationRequest
    ) {
        Page<OrderReportDTO> orderReportDTOs = statisticsService.getAllOrderStatistics(paginationRequest);
        CustomPageResponse<OrderReportResponse> orderReportResponse = OrderReportMapper.toOrderReportResponseList(orderReportDTOs);
        return CustomResponse.ok(orderReportResponse);
    }

}
