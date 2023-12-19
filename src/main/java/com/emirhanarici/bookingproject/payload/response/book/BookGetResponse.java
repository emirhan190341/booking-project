package com.emirhanarici.bookingproject.payload.response.book;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookGetResponse {
    private String id;
    private String isbn;
    private String name;
    private String authorFullName;
    private Integer stock;
    private BigDecimal price;
}