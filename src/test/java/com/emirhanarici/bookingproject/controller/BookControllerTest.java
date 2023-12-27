package com.emirhanarici.bookingproject.controller;

import com.emirhanarici.bookingproject.base.BaseControllerTest;
import com.emirhanarici.bookingproject.builder.BookBuilder;
import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.model.mapper.book.BookMapper;
import com.emirhanarici.bookingproject.payload.request.book.BookCreateRequest;
import com.emirhanarici.bookingproject.payload.response.CustomResponse;
import com.emirhanarici.bookingproject.payload.response.book.BookCreatedResponse;
import com.emirhanarici.bookingproject.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends BaseControllerTest {

    @MockBean
    private BookService bookService;

    @Test
    void givenCreateBookRequest_whenAdminRole_ReturnBookCreatedResponse() throws Exception {

        // Given
        BookCreateRequest mockRequest = BookCreateRequest.builder()
                .authorFullName("Author full name")
                .name("name")
                .isbn("1234567890")
                .stock(123)
                .price(BigDecimal.TEN)
                .build();

        Book book = new BookBuilder().withValidFields().build();

        BookDTO bookDTO = BookMapper.toDTO(book);

        // When
        BookCreatedResponse bookCreatedResponse = BookMapper.toCreatedResponse(bookDTO);
        CustomResponse<BookCreatedResponse> customResponseOfBookCreatedResponse = CustomResponse.created(bookCreatedResponse);

        Mockito.when(bookService.createBook(mockRequest)).thenReturn(bookDTO);

        // Then
        mockMvc.perform(post("/api/v1/books")
                        .header(HttpHeaders.AUTHORIZATION, mockAdminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.id").value(bookCreatedResponse.getId()))
                .andExpect(jsonPath("$.response.isbn").value(bookCreatedResponse.getIsbn()))
                .andExpect(jsonPath("$.response.name").value(bookCreatedResponse.getName()))
                .andExpect(jsonPath("$.response.authorFullName").value(bookCreatedResponse.getAuthorFullName()))
                .andExpect(jsonPath("$.response.stock").value(bookCreatedResponse.getStock()))
                .andExpect(jsonPath("$.isSuccess").value(customResponseOfBookCreatedResponse.getIsSuccess()))
                .andExpect(jsonPath("$.httpStatus").value(customResponseOfBookCreatedResponse.getHttpStatus().name()));
    }


}