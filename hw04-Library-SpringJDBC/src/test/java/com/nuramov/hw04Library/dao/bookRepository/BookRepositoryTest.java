package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
public class BookRepositoryTest {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    private BookRepository bookRepository;

    @BeforeEach
    void setUpBookRepository () {
        bookRepository = new BookRepositoryImpl(jdbcOperations);
    }

    @Test
    void countTest() {
        assertEquals(3, bookRepository.count());
    }

    @Test
    void saveTest() {
        Book newBook = getBook();

        // bookId = 4L, authorId = 3L, genreId = 3L при сохранении в БД
        bookRepository.save(newBook.getTitle(),
                newBook.getAuthor().getId(), newBook.getAuthor().getName(),
                newBook.getGenre().getId(), newBook.getGenre().getName());

        Book bookFromDataBase = getBookFromDataBase(4L);

        newBook.setId(4L);
        newBook.getAuthor().setId(3L);
        newBook.getGenre().setId(3L);

        assertNotNull(bookFromDataBase);
        assertEquals(newBook, bookFromDataBase);
        assertEquals(4, bookRepository.count());
    }

    @Test
    void updateTest() {
        Book book = getBookFromDataBase(3L);

        book.setTitle("newUpdatedTitle");
        book.setAuthor(getNewAuthorName());
        book.setGenre(getNewGenreName());

        bookRepository.update(book);

        Book updatedBook = getBookFromDataBase(3L);

        assertNotNull(updatedBook);
        assertEquals(book, updatedBook);
    }

    @Test
    void deleteByIdTest() {
        assertEquals(3, bookRepository.count());

        bookRepository.deleteById(2L);

        Book bookFromDataBase = getBookFromDataBase(2L);

        assertEquals(2, bookRepository.count());
        assertNull(bookFromDataBase);
    }

    @Test
    void findAllAndFindByIDTest() {
        assertEquals(3, bookRepository.count());

        Book book1 = getBookFromDataBase(1L);
        Book book2 = getBookFromDataBase(2L);
        Book book3 = getBookFromDataBase(3L);

        Book[] arrayOfBooks = new Book[3];
        arrayOfBooks[0] = book1;
        arrayOfBooks[1] = book2;
        arrayOfBooks[2] = book3;

        Book[] arrayOfBooksFromDataBase = new Book[3];
        List<Book> listOfBooksFromDataBase = bookRepository.findAll();
        int i = 0;
        for(Book book : listOfBooksFromDataBase) {
            arrayOfBooksFromDataBase[i] = book;
            i++;
        }

        assertArrayEquals(arrayOfBooks, arrayOfBooksFromDataBase);
    }

    private Book getBook() {
        Long id = 10L;

        Genre genre = new Genre();
        genre.setId(id);
        genre.setName("genreNameTest");

        Author author = new Author();
        author.setId(id);
        author.setName("authorNameTest");

        Book book = new Book();
        book.setId(id);
        book.setTitle("bookTitleTest");
        book.setGenre(genre);
        book.setAuthor(author);
        return book;
    }

    private Book getBookFromDataBase(Long id) {
        Book bookFromDataBase = null;
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            bookFromDataBase = optionalBook.get();
        }
        return bookFromDataBase;
    }

    private Author getNewAuthorName() {
        Author author = new Author();
        author.setId(4L);
        author.setName("newAuthorNameTest");
        return author;
    }

    private Genre getNewGenreName() {
        Genre genre = new Genre();
        genre.setId(4L);
        genre.setName("newGenreNameTest");
        return genre;
    }
}
