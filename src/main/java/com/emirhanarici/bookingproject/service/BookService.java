package com.emirhanarici.bookingproject.service;

import com.emirhanarici.bookingproject.dto.BookDTO;
import com.emirhanarici.bookingproject.exception.book.BookNotFoundException;
import com.emirhanarici.bookingproject.exception.book.NoAvailableStockException;
import com.emirhanarici.bookingproject.model.Book;
import com.emirhanarici.bookingproject.model.mapper.book.BookMapper;
import com.emirhanarici.bookingproject.payload.request.book.BookCreateRequest;
import com.emirhanarici.bookingproject.payload.request.book.BookUpdateRequest;
import com.emirhanarici.bookingproject.payload.request.book.BookUpdateStockRequest;
import com.emirhanarici.bookingproject.payload.request.pagination.PaginationRequest;
import com.emirhanarici.bookingproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public BookDTO updateBookById(final String bookId, final BookUpdateRequest request) {
        final Book bookEntityToBeUpdate = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        BookMapper.mapForUpdating(bookEntityToBeUpdate, request);

        return BookMapper.toDTO(bookRepository.save(bookEntityToBeUpdate));
    }

    @Transactional
    public BookDTO updateBookStockById(String bookId, BookUpdateStockRequest request) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setStock(request.getStock());

        return BookMapper.toDTO(bookRepository.save(book));
    }

    public boolean isStockAvailable(BookDTO bookDTO, int amount) {
        if (bookDTO.getStock() < amount) {
            throw new NoAvailableStockException(amount);
        } else {
            return true;
        }

    }


}
