package com.nuramov.hw04Library.shellComponent;

import com.nuramov.hw04Library.dao.bookRepository.BookRepository;
import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

/**
 * Класс LibraryShellCommands позволяет работать с основными командами CRUD-приложения через консоль
 */
@ShellComponent
public class LibraryShellCommands {

    private final BookRepository bookRepository;

    @Autowired
    public LibraryShellCommands(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ShellMethod(key = "count", value = "Show count of books in database.")
    public void booksCount() {
        System.out.println("Количество книг в БД: " + bookRepository.count());
    }

    @ShellMethod(key = "findById", value = "Find a book in the database by id.")
    public Book findBookById(long id) {
        return bookRepository.findById(id).get();
    }

    @ShellMethod(key = "delete", value = "Delete a book in the database by id.")
    public void deleteById(long id) {
        int result = bookRepository.deleteById(id);
        if(result > 0) {
            System.out.println("Книга удалена из БД");
        } else {
            System.out.println("Книга не удалена. Проверьте id и повторите еще раз");
        }
    }

    @ShellMethod(key = "findAll", value = "Find all books in database.")
    public void findAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        for(Book book : allBooks) {
            System.out.println(book);
        }
    }

    @ShellMethod (key = "save", value = "Save a book in database.")
    public void saveBook(long bookId, String bookName,
                         long authorId, String authorName,
                         long genreId, String genreName
    ) {
        Book book = getNewParametersOfBook(
                bookId, bookName, authorId, authorName, genreId, genreName
        );

        bookRepository.save(book);
        System.out.println(book + " успешно сохранен в БД");
    }

    @ShellMethod (key = "update", value = "Update the book in database")
    public void updateBook(long bookId, String bookName,
                           long authorId, String authorName,
                           long genreId, String genreName
    ) {
        Book book = getNewParametersOfBook(
                bookId, bookName, authorId, authorName, genreId, genreName
        );

        bookRepository.update(book);
        System.out.println(book + " успешно обновлен в БД");
    }

    /**
     * Метод getNewParametersOfBook позволяет получить экземпляр класса Book
     * с новыми характеристиками по заданным значениям параметров
     * @param bookId - id книги
     * @param bookName - название книги
     * @param authorId - id автора
     * @param authorName - имя автора
     * @param genreId - id жанра
     * @param genreName - название жанра
     * @return - возвращает экземпляр класса Book с новыми характеристиками
     */
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
