package com.nuramov.hw04Library.dao.bookRepository;

import com.nuramov.hw04Library.entities.Author;
import com.nuramov.hw04Library.entities.Book;
import com.nuramov.hw04Library.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
                .queryForObject("SELECT count(*) FROM BOOKS", Integer.class);
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
        // Обновляем Genre
        SqlParameterSource genreNamedParameters = new MapSqlParameterSource()
                .addValue("id", book.getGenre().getId())
                .addValue("name", book.getGenre().getName());
        jdbcOperations.update("UPDATE GENRE SET name = :name WHERE id = :id", genreNamedParameters);

        // Обновляем Author
        SqlParameterSource authorNamedParameters = new MapSqlParameterSource()
                .addValue("id", book.getAuthor().getId())
                .addValue("name", book.getAuthor().getName());
        jdbcOperations.update("UPDATE AUTHOR SET name = :name WHERE id = :id", genreNamedParameters);

        // Обновляем Books
        SqlParameterSource bookNamedParameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("GENRE_id", book.getGenre().getId())
                .addValue("AUTHOR_id", book.getAuthor().getId());
        return jdbcOperations.update(
                "UPDATE BOOKS SET title = :title, GENRE_id = :GENRE_id, AUTHOR_id = :AUTHOR_id WHERE id = :id",
                bookNamedParameters
        );
    }

    @Override
    public int deleteById(Long id) {
        return jdbcOperations.getJdbcOperations()
                .update("DELETE FROM BOOKS WHERE id = ?", id);
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
                        "SELECT * FROM BOOKS WHERE id = :id",
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
                    "SELECT name FROM GENRE WHERE id = " + genreId,
                    String.class
            );
            Genre genre = new Genre();
            genre.setId(genreId);
            genre.setName(genreName);
            book.setGenre(genre);

            long authorId = rs.getLong("AUTHOR_id");
            String authorName = jdbcOperations.getJdbcOperations().queryForObject(
                    "SELECT name FROM AUTHOR WHERE id = " + authorId,
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
