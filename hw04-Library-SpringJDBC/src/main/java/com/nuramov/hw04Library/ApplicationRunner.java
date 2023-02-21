package com.nuramov.hw04Library;

import com.nuramov.hw04Library.dao.bookRepository.BookRepository;
import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
//@ConfigurationPropertiesScan("com.baeldung.configurationproperties")
public class ApplicationRunner implements CommandLineRunner {

    /*@Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;*/

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        //ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        //QuestionnaireLauncher questionnaireLauncher = context.getBean(QuestionnaireLauncher.class);
        //questionnaireLauncher.run();

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
        book4.setTitle("bookTitle4");
        book4.setAuthor(author4);
        book4.setGenre(genre4);
        System.out.println(book4);

        int saveCheck = bookRepository.save(book4);
        System.out.println(saveCheck);

        Optional<Book> optionalBookCheck = bookRepository.findById(4L);
        Book bookCheck = optionalBookCheck.get();
        System.out.println(bookCheck);

        //List<Book> books = new ArrayList<>();
        //books = bookRepository.findAll();


        //Book book2 = bookRepository.findById(1L).get();
        //System.out.println(book2);
    }

}
