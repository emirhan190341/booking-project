package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.exception.book.BookNotFoundException;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.model.mapper.book.BookMapper;
import com.emirhanarici.bookingproject.payload.request.book.BookCreateRequest;
import com.emirhanarici.bookingproject.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * Creates a new book based on the provided request.
     *
     * @param request The request containing book information.
     * @return A {@link BookDTO} representing the newly created book.
     */
    public BookDTO createBook(BookCreateRequest request) {

        final Book bookEntityToBeSaved = BookMapper.mapForSaving(request);

        return BookMapper.toDTO(bookRepository.save(bookEntityToBeSaved));
    }


    @Transactional
    public BookDTO getBookById(final String bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        return BookMapper.toDTO(book);
    }

}
