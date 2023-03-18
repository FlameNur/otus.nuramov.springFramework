package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Работа теста осуществляется без Spring Shell.
 * Свойства БД, схема таблиц и их заполнение см. в resources
 */
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class BookRepositoryUpdateTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void update() {
        Book book = getBook();

        bookRepository.save(book);

        Optional<Book> optional = bookRepository.findById(10L);
        assertTrue(optional.isPresent());

        Book bookToUpdate = optional.get();
        bookToUpdate.setTitle("newTitle");
        bookRepository.update(bookToUpdate);

        Optional<Book> optionalToUpdate = bookRepository.findById(10L);
        assertTrue(optionalToUpdate.isPresent());
        assertEquals(bookToUpdate, optionalToUpdate.get());
    }


    private Book getBook() {
        Book book = new Book();

        Author author1 = new Author();
        author1.setId(10L);
        author1.setName("authorName1");

        Genre genre1 = new Genre();
        genre1.setId(10L);
        genre1.setName("genreName1");

        book.setId(10L);
        book.setTitle("bookTitle1");
        book.setAuthor(author1);
        book.setGenre(genre1);
        return book;
    }
}
