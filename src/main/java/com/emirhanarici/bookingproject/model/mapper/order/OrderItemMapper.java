package com.emirhanarici.bookingproject.model.mapper.order;

import com.emirhanarici.bookingproject.dto.OrderItemDTO;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.model.OrderItem;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

import static com.emirhanarici.bookingproject.model.mapper.book.BookMapper.toBook;

@UtilityClass
public class OrderItemMapper {

    public OrderItemDTO toDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .book(toBook(orderItem.getBook()))
                .build();
    }



    public List<OrderItemDTO> toDTO(List<OrderItem> orderItems) {

        return orderItems.stream()
                .map(OrderItemMapper::toDTO)
                .toList();
    }

    private OrderItemDTO.OrderItemBook toBook(Book source) {
        return OrderItemDTO.OrderItemBook.builder()
                .id(source.getId())
                .name(source.getName())
                .authorFullName(source.getAuthorFullName())
                .isbn(source.getIsbn())
                .price(source.getPrice())
                .build();
    }


    public OrderItem toOrderItem(OrderItemDTO orderItemDTO) {

        return OrderItem.builder()
                .id(orderItemDTO.getId())
                .book(toBook(orderItemDTO.getBook()))
                .build();
    }

    public List<OrderItem> toOrderItem(Collection<OrderItemDTO> orderItemDTOs) {

        return orderItemDTOs.stream().map(OrderItemMapper::toOrderItem).toList();
    }

    private static Book toBook(OrderItemDTO.OrderItemBook orderItemBook) {

        return Book.builder()
                .id(orderItemBook.getId())
                .isbn(orderItemBook.getIsbn())
                .name(orderItemBook.getName())
                .authorFullName(orderItemBook.getAuthorFullName())
                .price(orderItemBook.getPrice())
                .build();

    }




}
