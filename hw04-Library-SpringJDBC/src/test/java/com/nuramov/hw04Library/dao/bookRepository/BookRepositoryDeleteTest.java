package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Работа теста осуществляется без Spring Shell.
 * Свойства БД, схема таблиц и их заполнение см. в resources
 */
//@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class BookRepositoryDeleteTest {

    //@Autowired
    @Mock
    BookRepository bookRepository;

    @Test
    public void delete() {



        /*Optional<Book> optional = bookRepository.findById(1L);
        assertTrue(optional.isPresent());

        int result = bookRepository.deleteById(1L);
        assertEquals(1, result);

        Optional<Book> emptyOptional = bookRepository.findById(1L);
        assertTrue(emptyOptional.isEmpty());*/
    }
}
