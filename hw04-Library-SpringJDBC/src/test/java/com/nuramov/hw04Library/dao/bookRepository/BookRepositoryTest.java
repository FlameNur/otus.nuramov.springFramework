package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
//@Sql({"/data.sql", "/schema.sql"})
public class BookRepositoryTest {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private BookRepository bookRepository;

    @BeforeEach
    void setUpBookRepository () {
        bookRepository = new BookRepositoryImpl(jdbcOperations);
    }

    @Test
    void CountTest() {
        assertEquals(3, bookRepository.count());
    }

    @Test
    void SaveTest() {
        Book savedBook = getBook();

        bookRepository.save(savedBook);

        Book bookFromDataBase = null;
        Optional<Book> optionalBook = bookRepository.findById(10L);
        if(optionalBook.isPresent()) {
            bookFromDataBase = optionalBook.get();
        }

        assertNotNull(bookFromDataBase);
        assertEquals(savedBook, bookFromDataBase);
    }

    private Book getBook() {
        Genre genre = new Genre();
        genre.setId(10L);
        genre.setName("genreNameTest");

        Author author = new Author();
        author.setId(10L);
        author.setName("authorNameTest");

        Book book = new Book();
        book.setId(10L);
        book.setTitle("bookTitleTest");
        book.setGenre(genre);
        book.setAuthor(author);
        return book;
    }
}
