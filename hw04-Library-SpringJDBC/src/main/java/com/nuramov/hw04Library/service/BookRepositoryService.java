package com.nuramov.hw04Library.service;

import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.exceptions.BookDeleteException;
import com.nuramov.hw04Library.exceptions.BookSaveException;
import com.nuramov.hw04Library.exceptions.BookUpdateException;
import com.nuramov.hw04Library.exceptions.FindByIdException;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryService {

    int count();

    void save(Book book) throws BookSaveException;

    void update(Book book) throws BookUpdateException;

    void deleteById(Long id) throws BookDeleteException;

    List<Book> findAll();

    Optional<Book> findById(Long id) throws FindByIdException;

    Book getNewParametersOfBook (
            long bookId, String bookName,
            long authorId, String authorName,
            long genreId, String genreName
    );
}
