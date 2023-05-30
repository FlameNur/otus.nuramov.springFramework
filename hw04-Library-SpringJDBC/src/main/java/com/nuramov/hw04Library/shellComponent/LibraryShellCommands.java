package com.nuramov.hw04Library.shellComponent;

import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.exceptions.BookDeleteException;
import com.nuramov.hw04Library.exceptions.BookSaveException;
import com.nuramov.hw04Library.exceptions.BookUpdateException;
import com.nuramov.hw04Library.exceptions.FindByIdException;
import com.nuramov.hw04Library.service.BookRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

/**
 * Класс LibraryShellCommands позволяет работать с основными командами CRUD-приложения через консоль
 */
@ShellComponent
public class LibraryShellCommands {

    private final BookRepositoryService bookRepositoryService;

    @Autowired
    public LibraryShellCommands(BookRepositoryService bookRepositoryService) {
        this.bookRepositoryService = bookRepositoryService;
    }

    @ShellMethod(key = "count", value = "Show count of books in database.")
    public void booksCount() {
        System.out.println("Количество книг в БД: " + bookRepositoryService.count());
    }

    @ShellMethod(key = "findById", value = "Find a book in the database by id.")
    public Book findBookById(long id) {
        try {
            return bookRepositoryService.findById(id).get();
        } catch (FindByIdException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @ShellMethod(key = "delete", value = "Delete a book in the database by id.")
    public void deleteById(long id) {
        try {
            bookRepositoryService.deleteById(id);
            System.out.println("Книга успешно удалена");
        } catch (BookDeleteException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @ShellMethod(key = "findAll", value = "Find all books in database.")
    public void findAllBooks() {
        List<Book> allBooks = bookRepositoryService.findAll();
        for(Book book : allBooks) {
            System.out.println(book);
        }
    }

    @ShellMethod (key = "save", value = "Save a book in database.")
    public void saveBook(long bookId, String bookName,
                         long authorId, String authorName,
                         long genreId, String genreName
    ) {
        Book book = bookRepositoryService.getNewParametersOfBook(
                bookId, bookName, authorId, authorName, genreId, genreName
        );

        try {
            bookRepositoryService.save(book);
            System.out.println(book + " успешно сохранен в БД");
        } catch (BookSaveException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @ShellMethod (key = "update", value = "Update the book in database")
    public void updateBook(long bookId, String bookName,
                           long authorId, String authorName,
                           long genreId, String genreName
    ) {
        Book book = bookRepositoryService.getNewParametersOfBook(
                bookId, bookName, authorId, authorName, genreId, genreName
        );

        try {
            bookRepositoryService.update(book);
            System.out.println("Книга успешно обновлена");
        } catch (BookUpdateException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
