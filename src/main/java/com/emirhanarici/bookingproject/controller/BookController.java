package com.emirhanarici.bookingproject.controller;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.payload.response.book.BookCreatedResponse;
import com.emirhanarici.bookingproject.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<BookCreatedResponse> createBook(@RequestBody @Valid final BookCreateRequest request) {
        final BookDTO createdBookEntity = bookService.createBook(request);
        final BookCreatedResponse response = BookMapper.toCreatedResponse(createdBookEntity);

        return CustomResponse.created(response);
    }


}
