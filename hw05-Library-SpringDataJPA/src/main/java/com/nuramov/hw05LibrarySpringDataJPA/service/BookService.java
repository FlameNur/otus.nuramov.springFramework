package com.nuramov.hw05LibrarySpringDataJPA.service;

import com.nuramov.hw05LibrarySpringDataJPA.entities.Book;

import java.util.List;

public interface BookService {

    int count();

    void save(String bookTitle,
              long authorId, String authorName,
              long genreId, String genreName
    );

    void update(long bookId, String bookTitle,
                long authorId, String authorName,
                long genreId, String genreName
    );

    void deleteById(Long id);

    List<Book> findAll();

    Book findById(Long id);
}
