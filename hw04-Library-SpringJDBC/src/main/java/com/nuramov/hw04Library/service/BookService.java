package com.nuramov.hw04Library.service;

import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.exceptions.BookDeleteException;
import com.nuramov.hw04Library.exceptions.BookSaveException;
import com.nuramov.hw04Library.exceptions.BookUpdateException;

import java.util.List;

public interface BookService {

    int count();

    void save(String bookName,
              long authorId, String authorName,
              long genreId, String genreName
    ) throws BookSaveException;

    void update(long bookId, String bookName,
                long authorId, String authorName,
                long genreId, String genreName
    ) throws BookUpdateException;

    void deleteById(Long id) throws BookDeleteException;

    List<Book> findAll();

    Book findById(Long id);
}
