package com.emirhanarici.bookingproject.controller;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.model.mapper.book.BookMapper;
import com.emirhanarici.bookingproject.payload.request.book.BookCreateRequest;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginationRequest;
import com.emirhanarici.bookingproject.payload.response.CustomPageResponse;
import com.emirhanarici.bookingproject.payload.response.CustomResponse;
import com.emirhanarici.bookingproject.payload.response.book.BookCreatedResponse;
import com.emirhanarici.bookingproject.payload.response.book.BookGetResponse;
import com.emirhanarici.bookingproject.payload.request.book.BookUpdateRequest;
import com.emirhanarici.bookingproject.payload.response.book.BookUpdatedResponse;
import com.emirhanarici.bookingproject.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{bookId}")
    public CustomResponse<BookGetResponse> getBookById(@PathVariable("bookId") final String bookId) {
        final BookDTO bookEntityFromDb = bookService.getBookById(bookId);
        final BookGetResponse response = BookMapper.toGetResponse(bookEntityFromDb);

        return CustomResponse.ok(response);
    }

    @PostMapping("/all")
    public CustomResponse<CustomPageResponse<BookGetResponse>> getBooks(@RequestBody @Valid PaginationRequest paginationRequest) {
        final Page<BookDTO> bookEntitiesFromDb = bookService.getAllBooks(paginationRequest);
        final CustomPageResponse<BookGetResponse> responses = BookMapper
                .toGetResponse(bookEntitiesFromDb);

        return CustomResponse.ok(responses);
    }

    @PutMapping("/{bookId}")
    public CustomResponse<BookUpdatedResponse> updateBookByBookId(@PathVariable("bookId") final String bookId,
                                                                  @RequestBody @Valid final BookUpdateRequest request) {
        final BookDTO updatedBookEntity = bookService
                .updateBookById(bookId, request);
        final BookUpdatedResponse response = BookMapper
                .toUpdatedResponse(updatedBookEntity);

        return CustomResponse.ok(response);
    }



}
