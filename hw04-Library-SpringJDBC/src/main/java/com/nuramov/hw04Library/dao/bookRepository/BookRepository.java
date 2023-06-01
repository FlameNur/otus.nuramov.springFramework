package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    int count();

    int save(String bookTitle,
             long authorId, String authorName,
             long genreId, String genreName
    );

    int update(Book book);

    int deleteById(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);
}
