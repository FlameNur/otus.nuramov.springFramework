package com.nuramov.hw04Library.service;

import com.nuramov.hw04Library.dao.bookRepository.BookRepository;
import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import com.nuramov.hw04Library.exceptions.BookDeleteException;
import com.nuramov.hw04Library.exceptions.BookSaveException;
import com.nuramov.hw04Library.exceptions.BookUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public void save(String bookName,
                     long authorId, String authorName,
                     long genreId, String genreName
    ) throws BookSaveException {
        // Проверяем наличие книги в БД
        //Optional<Book> optionalBook = bookRepository.findById(bookId());  // Надо переделать
        //if(optionalBook.isPresent()) throw new BookSaveException();

        //int saveResult = bookRepository.save(book);
        //if(saveResult == 0) throw new BookSaveException();
    }

    @Override
    public void update(long bookId, String bookName,
                       long authorId, String authorName,
                       long genreId, String genreName
    ) throws BookUpdateException {

        // Проверяем наличие книги в БД
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()) throw new BookUpdateException();

        Book book = getNewParametersOfBook(bookId, bookName, authorId, authorName, genreId, genreName);

        int updateResult = bookRepository.update(book);
        if(updateResult == 0) throw new BookUpdateException();
    }

    @Override
    public void deleteById(Long id) throws BookDeleteException {
        int deleteResult = bookRepository.deleteById(id);
        if(deleteResult == 0) throw new BookDeleteException();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    private Book getNewParametersOfBook (long bookId, String bookName,
                                         long authorId, String authorName,
                                         long genreId, String genreName
    ) {
        Author author = new Author();
        author.setId(authorId);
        author.setName(authorName);

        Genre genre = new Genre();
        genre.setId(genreId);
        genre.setName(genreName);

        Book book = new Book();
        book.setId(bookId);
        book.setTitle(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }
}
