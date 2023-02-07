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

@SpringBootApplication
//@ConfigurationPropertiesScan("com.baeldung.configurationproperties")
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        //namedParameterJdbcTemplate.getJdbcOperations().execute("DROP TABLE IF EXISTS BOOKS");

        //namedParameterJdbcTemplate.getJdbcOperations().execute("INSERT INTO AUTHOR(FIRSTNAME, lastname) VALUES ('sfs', 'AA')");
        //namedParameterJdbcTemplate.getJdbcOperations().execute("INSERT INTO GENRE (name) VALUES ('DD')");
        //namedParameterJdbcTemplate.getJdbcOperations().execute("INSERT INTO BOOKS (title, AUTHOR_lastname, GENRE_name) VALUES ('fssfs', (SELECT lastname FROM AUTHOR WHERE lastname = 'AA'), (SELECT name FROM GENRE WHERE name = 'DD'))");

        //Book book = bookRepository.findById(2L).orElseThrow(IllegalArgumentException::new);
        //System.out.println(book);

        System.out.println(bookRepository.count());

        Author author = new Author("AA", "BB");
        Genre genre = new Genre("GG");
        Book book = new Book(1L,"TT", author, genre);

        //bookRepository.save(book);
    }

}
