package com.emirhanarici.bookingproject.model.mapper.book;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.payload.request.book.BookCreateRequest;
import com.emirhanarici.bookingproject.payload.response.book.BookCreatedResponse;
import com.emirhanarici.bookingproject.payload.response.book.BookGetResponse;
import lombok.experimental.UtilityClass;

/**
 * Utility class for mapping between {@link Book} entities and DTOs.
 */
@UtilityClass
public class BookMapper {

    /**
     * Maps a {@link BookCreateRequest} to a {@link Book} entity for saving.
     *
     * @param request The {@link BookCreateRequest} to be mapped.
     * @return A new {@link Book} entity populated with data from the request.
     */
    public static Book mapForSaving(BookCreateRequest request) {
        return Book.builder()
                .isbn(request.getIsbn())
                .name(request.getName())
                .authorFullName(request.getAuthorFullName())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();
    }

    /**
     * Converts a {@link BookDTO} to a {@link BookCreatedResponse}.
     *
     * @param source The source {@link BookDTO} to be converted.
     * @return A {@link BookCreatedResponse} containing data from the source DTO.
     */
    public static BookCreatedResponse toCreatedResponse(BookDTO source) {
        return BookCreatedResponse.builder()
                .id(source.getId())
                .isbn(source.getIsbn())
                .name(source.getName())
                .authorFullName(source.getAuthorFullName())
                .stock(source.getStock())
                .price(source.getPrice())
                .build();
    }

    public static BookGetResponse toGetResponse(BookDTO source) {
        if (source == null) {
            return null;
        }

        return BookGetResponse.builder()
                .id(source.getId())
                .isbn(source.getIsbn())
                .name(source.getName())
                .authorFullName(source.getAuthorFullName())
                .stock(source.getStock())
                .price(source.getPrice())
                .build();

    }

    /**
     * Converts a {@link Book} entity to a {@link BookDTO}.
     *
     * @param book The {@link Book} entity to be converted.
     * @return A {@link BookDTO} containing data from the source entity.
     */
    public static BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .authorFullName(book.getAuthorFullName())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .stock(book.getStock())
                .build();
    }

}