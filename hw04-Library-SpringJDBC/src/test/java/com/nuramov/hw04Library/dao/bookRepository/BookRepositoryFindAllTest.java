package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Работа теста осуществляется без Spring Shell.
 * Свойства БД, схема таблиц и их заполнение см. в resources
 */
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class BookRepositoryFindAllTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void findAll() {
        List<Book> books = bookRepository.findAll();

        assertNotNull(books);
        // В БД сохранено 3 книги через data.sql
        assertEquals(3, books.size());

        Book[] booksArrayFindAll = new Book[(books.size())];
        books.toArray(booksArrayFindAll);

        Book[] booksArrayFindById = new Book[(books.size())];
        booksArrayFindById[0] = bookRepository.findById(1L).get();
        booksArrayFindById[1] = bookRepository.findById(2L).get();
        booksArrayFindById[2] = bookRepository.findById(3L).get();

        assertArrayEquals(booksArrayFindById, booksArrayFindAll);

        bookRepository.deleteById(1L);
        bookRepository.deleteById(2L);
        bookRepository.deleteById(3L);

        books = bookRepository.findAll();
        assertNotNull(books);
        assertEquals(0, books.size());
    }
}
