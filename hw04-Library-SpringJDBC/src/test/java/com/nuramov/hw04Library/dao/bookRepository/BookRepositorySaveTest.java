package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Работа теста осуществляется без Spring Shell.
 * Свойства БД, схема таблиц и их заполнение см. в resources
 */
//@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookRepositorySaveTest {

    //@Autowired
    BookRepository bookRepository;

    @Test
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void save() {
        /*Book book = getBook();
        int result = bookRepository.save(book);

        assertEquals(1, result);
        // В БД сохранено 3 книги через data.sql +1
        assertEquals(4, bookRepository.count());
        assertNotNull(bookRepository.findById(10L));

        Optional<Book> optional = bookRepository.findById(10L);
        assertTrue(optional.isPresent());
        assertEquals(book, optional.get());*/
    }

    private Book getBook() {
        Book book = new Book();

        Author author1 = new Author();
        author1.setId(10L);
        author1.setName("authorName10");

        Genre genre1 = new Genre();
        genre1.setId(10L);
        genre1.setName("genreName10");

        book.setId(10L);
        book.setTitle("bookTitle10");
        book.setAuthor(author1);
        book.setGenre(genre1);
        return book;
    }
}