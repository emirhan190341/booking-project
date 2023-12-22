package com.emirhanarici.bookingproject.model.mapper.book;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.payload.request.book.BookCreateRequest;
import com.emirhanarici.bookingproject.payload.request.book.BookUpdateRequest;
import com.emirhanarici.bookingproject.payload.response.CustomPageResponse;
import com.emirhanarici.bookingproject.payload.response.book.BookCreatedResponse;
import com.emirhanarici.bookingproject.payload.response.book.BookGetResponse;
import com.emirhanarici.bookingproject.payload.response.book.BookUpdatedResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

/**
 * Utility class for mapping between {@link Book} entities and DTOs.
 */
@UtilityClass
public class BookMapper {


    public static Book mapForSaving(BookCreateRequest request) {
        return Book.builder()
                .isbn(request.getIsbn())
                .name(request.getName())
                .authorFullName(request.getAuthorFullName())
                .stock(request.getStock())
                .price(request.getPrice())
                .build();
    }

    public static void mapForUpdating(Book bookEntityToBeUpdate, BookUpdateRequest request) {
        bookEntityToBeUpdate.setIsbn(request.getIsbn());
        bookEntityToBeUpdate.setName(request.getName());
        bookEntityToBeUpdate.setAuthorFullName(request.getAuthorFullName());
        bookEntityToBeUpdate.setStock(request.getStock());
        bookEntityToBeUpdate.setPrice(request.getPrice());
    }


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

    public static CustomPageResponse<BookGetResponse> toGetResponse(Page<BookDTO> sources) {

        return CustomPageResponse.of(sources.map(BookMapper::toGetResponse));
    }

    public static BookUpdatedResponse toUpdatedResponse(BookDTO source) {
        if (source == null) {
            return null;
        }

        return BookUpdatedResponse.builder()
                .id(source.getId())
                .isbn(source.getIsbn())
                .name(source.getName())
                .authorFullName(source.getAuthorFullName())
                .stock(source.getStock())
                .price(source.getPrice())
                .build();
    }

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

    public static Book toBook(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .name(bookDTO.getName())
                .authorFullName(bookDTO.getAuthorFullName())
                .isbn(bookDTO.getIsbn())
                .price(bookDTO.getPrice())
                .stock(bookDTO.getStock())
                .build();

    }


}