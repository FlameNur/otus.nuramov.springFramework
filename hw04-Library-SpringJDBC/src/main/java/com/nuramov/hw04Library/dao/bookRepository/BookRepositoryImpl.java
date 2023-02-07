package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
        return namedParameterJdbcTemplate.getJdbcOperations()
                .queryForObject("select count(*) from BOOKS", Integer.class);
    }

    @Override
    public int save(Book book) {
        return namedParameterJdbcTemplate.getJdbcOperations()
                .update("insert into BOOKS (title, AUTHOR_lastname, GENRE_name) values(?,?,?)",
                        book.getTitle(), book.getAuthor(), book.getGenre());
    }

    @Override
    public int update(Book book) {
        return namedParameterJdbcTemplate.update(
                "update BOOKS set title = :title, AUTHOR_lastname = :author, GENRE_name =: genre where id = :id",
                new BeanPropertySqlParameterSource(book));
    }

    @Override
    public int deleteById(Long id) {
        return namedParameterJdbcTemplate.getJdbcOperations()
                .update("delete from BOOKS where id = ?", id);
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcTemplate.getJdbcOperations()
                .query(
                        "select * from BOOKS",
                        (rs, rowNum) ->
                                new Book(
                                        rs.getLong("id"),
                                        rs.getString("name"),
                                        rs.getObject(3, Author.class),
                                        rs.getObject(4, Genre.class)
                                )
                );
    }

    @Override
    public Optional<Book> findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from BOOKS where id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        Optional.of(new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getObject(3, Author.class),
                                rs.getObject(4, Genre.class)
                        ))
        );
    }
}
