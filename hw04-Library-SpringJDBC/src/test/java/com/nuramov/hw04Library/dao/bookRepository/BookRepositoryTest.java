package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookRepositoryTest {

    private static final long ID = 1L;

    @Mock
    BookRepository bookRepository;

    @Test
    public void findByIdTest() {
        /*final Book book = mock(Book.class);
        //final Book book = getBook();
        when(book.getId()).thenReturn(ID);

        bookRepository.save(book);


        when(bookRepository.findById(ID)).thenReturn(Optional.of(book));  //????


        Optional<Book> optionalBook = bookRepository.findById(ID);
        Book actualBook = null;
        if(optionalBook.isPresent()) {
            actualBook = optionalBook.get();
        }


        assertNotNull(actualBook);
        assertEquals(book, actualBook);*/


        //when(bookRepository.findById(ID)).thenReturn(Optional.of(book));
    }






    @Test
    public void bookRepositoryCountTest() {

    }

    private List<Book> getListOfBooks() {
        List<Book> listOfBooks = new ArrayList<>();



        return listOfBooks;
    }

    private Book getBook() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("genreName");

        Author author = new Author();
        author.setId(1L);
        author.setName("authorName");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("bookTitle");
        book.setGenre(genre);
        book.setAuthor(author);

        return book;
    }

}
