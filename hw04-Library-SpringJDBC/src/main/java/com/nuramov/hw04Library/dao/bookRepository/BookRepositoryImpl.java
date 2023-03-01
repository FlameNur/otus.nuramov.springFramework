package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
        // Сохраняем в Genre
        final HashMap<String, Object> genreParams = new HashMap<>(2);
        genreParams.put("id", book.getGenre().getId());
        genreParams.put("name", book.getGenre().getName());
        jdbcOperations.update("INSERT INTO GENRE (id, name) VALUES (:id, :name)", genreParams);

        // Сохраняем в Author
        final HashMap<String, Object> authorParams = new HashMap<>(2);
        authorParams.put("id", book.getAuthor().getId());
        authorParams.put("name", book.getAuthor().getName());
        jdbcOperations.update("INSERT INTO AUTHOR (id, name) VALUES (:id, :name)", authorParams);

        // Сохраняем в Books
        final HashMap<String, Object> bookParams = new HashMap<>(4);
        bookParams.put("id", book.getId());
        bookParams.put("title", book.getTitle());
        bookParams.put("GENRE_id", book.getGenre().getId());
        bookParams.put("AUTHOR_id", book.getAuthor().getId());
        return jdbcOperations.update(
                "INSERT INTO BOOKS (id, title, GENRE_id, AUTHOR_id) VALUES (:id, :title, :GENRE_id, :AUTHOR_id)",
                bookParams
        );
    }

    @Override
    public int update(Book book) {
        // Добавить для всех таблиц!!!!!!!!!!!!!!!!!!!


        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("GENRE_id", book.getGenre().getId())
                .addValue("AUTHOR_id", book.getAuthor().getId());
        int status = jdbcOperations.update(
                "UPDATE BOOKS set title = :title, GENRE_id = :GENRE_id, AUTHOR_id = :AUTHOR_id where id = :id",
                namedParameters
        );
        return status;
    }

    @Override
    public int deleteById(Long id) {
        return jdbcOperations.getJdbcOperations()
                .update("delete from BOOKS where id = ?", id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcOperations.query(
                "SELECT * FROM BOOKS",
                new BookRowMapper(jdbcOperations)
        );
    }

    @Override
    public Optional<Book> findById(Long id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return Optional.ofNullable(
                jdbcOperations.queryForObject(
                        "select * from books where id = :id",
                        params,
                        new BookRowMapper(jdbcOperations)
                )
        );
    }

    private static class BookRowMapper implements RowMapper<Book> {

        private final NamedParameterJdbcOperations jdbcOperations;

        private BookRowMapper(NamedParameterJdbcOperations jdbcOperations) {
            this.jdbcOperations = jdbcOperations;
        }

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));

            long genreId = rs.getLong("GENRE_id");
            String genreName = jdbcOperations.getJdbcOperations().queryForObject(
                    "SELECT NAME FROM GENRE WHERE ID = " + genreId,
                    String.class
            );
            Genre genre = new Genre();
            genre.setId(genreId);
            genre.setName(genreName);
            book.setGenre(genre);

            long authorId = rs.getLong("AUTHOR_id");
            String authorName = jdbcOperations.getJdbcOperations().queryForObject(
                    "SELECT NAME FROM AUTHOR WHERE ID = " + authorId,
                    String.class
            );
            Author author = new Author();
            author.setId(authorId);
            author.setName(authorName);
            book.setAuthor(author);
            return book;
        }
    }
}
