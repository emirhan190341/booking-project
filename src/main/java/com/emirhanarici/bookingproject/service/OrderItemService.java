package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.dto.OrderItemDTO;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.model.OrderItem;
import com.emirhanarici.bookingproject.model.mapper.book.BookMapper;
import com.emirhanarici.bookingproject.model.mapper.order.OrderItemMapper;
import com.emirhanarici.bookingproject.payload.request.book.BookUpdateStockRequest;
import com.emirhanarici.bookingproject.payload.request.order.OrderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final BookService bookService;

    @Transactional
    public OrderItemDTO createOrderItem(OrderItemRequest orderDetailRequest) {

        final BookDTO bookDTO = bookService.getBookById(orderDetailRequest.getBookId());
        final Book book = BookMapper.toBook(bookDTO);

        bookService.isStockAvailable(bookDTO, orderDetailRequest.getAmount());

        final OrderItem orderItem = OrderItem.builder()
                .book(book)
                .build();

        BookUpdateStockRequest bookUpdateStockRequest = BookUpdateStockRequest.builder()
                .stock(bookDTO.getStock() - orderDetailRequest.getAmount())
                .build();

        bookService.updateBookStockById(bookDTO.getId(), bookUpdateStockRequest);

        return OrderItemMapper.toDTO(orderItem);

    }
}
