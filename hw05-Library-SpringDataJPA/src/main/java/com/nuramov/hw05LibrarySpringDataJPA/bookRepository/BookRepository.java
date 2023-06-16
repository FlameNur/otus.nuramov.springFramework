package com.nuramov.hw05LibrarySpringDataJPA.bookRepository;

import com.nuramov.hw05LibrarySpringDataJPA.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    int count();

    int save(Book book);

    int update(Book book);

    int deleteById(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);
}
