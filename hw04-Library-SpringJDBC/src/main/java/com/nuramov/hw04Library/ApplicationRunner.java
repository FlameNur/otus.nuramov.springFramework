package com.nuramov.hw04Library;

import com.nuramov.hw04Library.dao.bookRepository.BookRepository;
import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        // Создали книгу
        Author author1 = new Author();
        author1.setId(10L);
        author1.setFirstName("FirstName1");
        author1.setLastName("LastName1");

        Author author2 = new Author();
        author2.setId(11L);
        author2.setFirstName("FirstName2");
        author2.setLastName("LastName2");

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        Genre genre1 = new Genre();
        genre1.setId(10L);
        genre1.setName("genre1");

        Book book1 = new Book();
        book1.setId(10L);
        book1.setTitle("Title1");
        book1.setGenre(genre1);
        book1.setAuthors(authors);

        System.out.println(book1);


        // Отрабатываем работу с БД
        System.out.println("Количество книг в библиотеке: " + bookRepository.count());


        Optional<Book> opt = bookRepository.findById(1L);
        Book book4 = opt.get();
        System.out.println(book4);


        //bookRepository.save(book);

        //List<Book> books = new ArrayList<>();
        //books = bookRepository.findAll();


        //Book book2 = bookRepository.findById(1L).get();
        //System.out.println(book2);
    }

}
