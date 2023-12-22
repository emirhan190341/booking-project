package com.emirhanarici.bookingproject.payload.request.pagination;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedFindAllRequest {

    @Valid
    private DateIntervalRequest dateIntervalRequest;

    @Valid
    private PaginationRequest paginationRequest;
}
