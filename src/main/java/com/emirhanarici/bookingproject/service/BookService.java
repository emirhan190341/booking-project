package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.exception.book.BookNotFoundException;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.model.mapper.book.BookMapper;
import com.emirhanarici.bookingproject.payload.request.book.BookCreateRequest;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginationRequest;
import com.emirhanarici.bookingproject.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public BookDTO createBook(BookCreateRequest request) {

        final Book bookEntityToBeSaved = BookMapper.mapForSaving(request);

        return BookMapper.toDTO(bookRepository.save(bookEntityToBeSaved));
    }


    @Transactional
    public BookDTO getBookById(final String bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        return BookMapper.toDTO(book);
    }

    public Page<BookDTO> getAllBooks(PaginationRequest paginationRequest) {

        return bookRepository
                .findAll(paginationRequest.toPageable())
                .map(BookMapper::toDTO);
    }
}
