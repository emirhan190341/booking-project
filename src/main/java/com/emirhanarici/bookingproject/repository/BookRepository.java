package com.emirhanarici.bookingproject.repository;

import com.emirhanarici.bookingproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
