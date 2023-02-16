package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository{

    private final NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public BookRepositoryImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations()
                .queryForObject("select count(*) from BOOKS", Integer.class);
    }

    //Надо переделать
    @Override
    public int save(Book book) {
        /*return jdbcOperations.getJdbcOperations()
                .update("insert into BOOKS (title, AUTHOR_lastname, GENRE_name) values(?,?,?)",
                        book.getTitle(), book.getAuthor(), book.getGenre());*/
        return 0;
    }

    @Override
    public int update(Book book) {
        return jdbcOperations.update(
                "update BOOKS set title = :title, AUTHOR_lastname = :author, GENRE_name =: genre where id = :id",
                new BeanPropertySqlParameterSource(book));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcOperations.getJdbcOperations()
                .update("delete from BOOKS where id = ?", id);
    }


    //НАДО ПЕРЕДЕЛАТЬ ПО ПРИМЕРУ НИЖЕ, НО ПОКА УБРАЛ КОНСТРУКТОР ИЗ КЛАССА Book
    @Override
    public List<Book> findAll() {
        return null;
    }

    // Пробуем
    @Override
    public Optional<Book> findById(Long id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return Optional.ofNullable(
                jdbcOperations.queryForObject(
                        "select * from books where id = :id",
                        params,
                        new BookRowMapper()
                )
        );
    }



    /*@Override
    public List<Book> findAll() {
        return namedParameterJdbcTemplate.getJdbcOperations()
                .query(
                        "select * from BOOKS",
                        (rs, rowNum) ->
                                new Book(
                                        rs.getLong("id"),
                                        rs.getString("title"),
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
                                rs.getString("title"),
                                rs.getObject(3, Author.class),
                                rs.getObject(4, Genre.class)
                        ))
        );
    }*/

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));

            Array arrayOfNames = rs.getArray("authorLastnames");
            String[] arrayOfNamesStr = (String[])arrayOfNames.getArray();
            List<String> authorLastnames = Arrays.asList(arrayOfNamesStr);
            book.setAuthorLastnames(authorLastnames);

            book.setGenreName(rs.getString("genreName"));
            return book;
        }
    }
}
