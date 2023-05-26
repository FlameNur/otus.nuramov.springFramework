package com.nuramov.hw04Library.service;

import com.nuramov.hw04Library.dao.bookRepository.BookRepository;
import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import com.nuramov.hw04Library.exceptions.FindByIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookRepositoryServiceImpl implements BookRepositoryService{

    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public int save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public int update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public int deleteById(Long id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) throws FindByIdException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook;
        } else {
            throw new FindByIdException();
        }
    }

    @Override
    public Book getNewParametersOfBook (long bookId, String bookName,
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
