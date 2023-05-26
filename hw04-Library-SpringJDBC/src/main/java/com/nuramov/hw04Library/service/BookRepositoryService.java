package com.nuramov.hw04Library.service;

import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.exceptions.FindByIdException;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryService {

    int count();

    int save(Book book);

    int update(Book book);

    int deleteById(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id) throws FindByIdException;

    Book getNewParametersOfBook (
            long bookId, String bookName,
            long authorId, String authorName,
            long genreId, String genreName
    );
}
