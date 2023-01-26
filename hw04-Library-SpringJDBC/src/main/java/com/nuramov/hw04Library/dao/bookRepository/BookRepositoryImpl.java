package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int save(Book book) {
        return 0;
    }

    @Override
    public int update(Book book) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findByName(String name) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public String getNameById(Long id) {
        return null;
    }
}
