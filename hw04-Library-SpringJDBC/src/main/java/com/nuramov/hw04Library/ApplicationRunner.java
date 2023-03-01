package com.nuramov.hw04Library;

import com.nuramov.hw04Library.dao.bookRepository.BookRepository;
import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);

    }

    @Override
    public void run(String... args) {
        runJDBC();
    }

    void runJDBC() {
        // Отрабатываем работу с БД
        System.out.println("Количество книг в библиотеке: " + bookRepository.count());

        // findById
        Optional<Book> optionalBook1 = bookRepository.findById(1L);
        Book book1 = optionalBook1.get();
        System.out.println(book1);

        // save
        Author author4 = new Author();
        author4.setId(4L);
        author4.setName("authorName4");

        Genre genre4 = new Genre();
        genre4.setId(4L);
        genre4.setName("genreName4");

        Book book4 = new Book();
        book4.setId(4L);
        book4.setTitle("bookTitle4");
        book4.setAuthor(author4);
        book4.setGenre(genre4);
        System.out.println(book4);

        System.out.println("Genre 4 id: " + book4.getGenre().getId());


        int deleteCheck = bookRepository.deleteById(1L);
        System.out.println("Проверка удаления " + deleteCheck);
        System.out.println("Количество книг в библиотеке после удаления: " + bookRepository.count());


        // Проверяем update
        Optional<Book> optionalBook2 = bookRepository.findById(2L);
        Book book2 = optionalBook2.get();
        System.out.println("До обновления: " + book2);

        book2.setTitle("bookName2AAAA");
        // Нашел косяк__________________________________________________________________________!!!!!!!!!!!!!
        book2.getGenre().setName("genreName2AAA");
        int updateCheck = bookRepository.update(book2);
        System.out.println("Проверка обновления " + updateCheck);

        Optional<Book> optionalBookUpdateCheck = bookRepository.findById(2L);
        book2 = optionalBookUpdateCheck.get();
        System.out.println("После обновления: " + book2);

        // Сохраняем
        int saveCheck = bookRepository.save(book4);
        System.out.println("Проверка после сохранения " + saveCheck);
        System.out.println("Количество книг в библиотеке: " + bookRepository.count());

        Optional<Book> optionalBookSaveCheck = bookRepository.findById(4L);
        Book savedBook = optionalBookSaveCheck.get();
        System.out.println("После сохранения: " + savedBook);

        // Все книги
        List<Book> allBooks = bookRepository.findAll();
        for(Book book : allBooks) {
            System.out.println(book);
        }
    }
}
