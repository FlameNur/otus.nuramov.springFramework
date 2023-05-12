package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Работа теста осуществляется без Spring Shell.
 * Свойства БД, схема таблиц и их заполнение см. в resources
 */
//@JdbcTest
//@Sql({"classpath: schema.sql", "classpath: data.sql"})
//@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class BookRepositoryFindByIdTest {

    //@Autowired
    BookRepository bookRepository;

    @Test
    public void findById() {
        /*Optional<Book> optional = bookRepository.findById(1L);
        assertTrue(optional.isPresent());

        optional = bookRepository.findById(100L);
        assertTrue(optional.isEmpty());*/
    }
}
