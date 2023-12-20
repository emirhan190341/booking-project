package com.emirhanarici.bookingproject.payload.request.pagination;

import org.springframework.data.domain.Pageable;

public interface Paging {

    /**
     * Converts the fields of implementing instances to a {@link Pageable}.
     *
     * @return Pageable
     */
    Pageable toPageable();

}
