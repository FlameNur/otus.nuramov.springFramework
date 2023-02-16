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


        System.out.println("Количество книг в библиотеке: " + bookRepository.count());

        //Author author = new Author("AA", "BB");
        //Genre genre = new Genre("GG");
        Book book = new Book();

        Optional<Book> opt = bookRepository.findById(1L);
        book = opt.get();
        System.out.println(book);


        //bookRepository.save(book);

        List<Book> books = new ArrayList<>();
        //books = bookRepository.findAll();


        //Book book2 = bookRepository.findById(1L).get();
        //System.out.println(book2);
    }

}
