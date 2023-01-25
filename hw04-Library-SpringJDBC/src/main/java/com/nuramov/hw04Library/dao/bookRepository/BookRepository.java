package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    int count();

    int save(Book book);

    int update(Book book);

    int deleteById(Long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    Optional<Book> findById(Long id);

    String getNameById(Long id);
}
