package com.emirhanarici.bookingproject.model.mapper.order;

import com.emirhanarici.bookingproject.dto.OrderReportDTO;
import com.emirhanarici.bookingproject.payload.response.CustomPageResponse;
import com.emirhanarici.bookingproject.payload.response.order.OrderReportResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

@UtilityClass
public class OrderReportMapper {

    public static OrderReportResponse toOrderReportResponse(OrderReportDTO orderReportDTO) {
        return OrderReportResponse.builder()
                .year(orderReportDTO.getYear())
                .month(orderReportDTO.getMonth())
                .totalOrderCount(orderReportDTO.getTotalOrderCount())
                .totalBookCount(orderReportDTO.getTotalBookCount())
                .totalPrice(orderReportDTO.getTotalPrice())
                .build();
    }


    public static CustomPageResponse<OrderReportResponse> toOrderReportResponseList(Page<OrderReportDTO> sources) {
        return CustomPageResponse.of(sources.map(OrderReportMapper::toOrderReportResponse));
    }
}
