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
        Integer count = jdbcOperations.getJdbcOperations()
                .queryForObject("SELECT count(*) FROM BOOKS", Integer.class);

        if (count != null) return count;
        else return 0;
    }

    @Override
    public int save(String bookTitle,
                    long authorId, String authorName,
                    long genreId, String genreName
    ) {
        // Сохраняем author, если его нет в БД. Определяем и передаем его новый id
        if(!authorCheckInDB(authorId)) {
            authorSave(authorName);
            authorId = getAuthorIdFromDB(authorName);
        }
        // Сохраняем genre, если его нет в БД. Определяем и передаем его новый id
        if(!genreCheckInDB(genreId)) {
            genreSave(genreName);
            genreId = getGenreIdFromDB(genreName);
        }
        // Сохраняем в Books
        final HashMap<String, Object> bookParams = new HashMap<>(3);
        bookParams.put("title", bookTitle);
        bookParams.put("GENRE_id", genreId);
        bookParams.put("AUTHOR_id", authorId);
        return jdbcOperations.update(
                "INSERT INTO BOOKS (id, title, GENRE_id, AUTHOR_id) VALUES (default, :title, :GENRE_id, :AUTHOR_id)",
                bookParams
        );
    }

    @Override
    public int update(Book book) {
        Long genreId = book.getGenre().getId();
        String genreName = book.getGenre().getName();
        Long authorId = book.getAuthor().getId();
        String authorName = book.getAuthor().getName();

        // Обновляем Genre
        if(genreCheckInDB(genreId)) {
            genreUpdate(book);
        } else {
            genreSave(genreName);
            genreId = getGenreIdFromDB(genreName);
        }
        // Обновляем Author
        if(authorCheckInDB(authorId)) {
            authorUpdate(book);
        } else {
            authorSave(authorName);
            authorId = getAuthorIdFromDB(authorName);
        }
        // Обновляем Books
        SqlParameterSource bookNamedParameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("GENRE_id", genreId)
                .addValue("AUTHOR_id", authorId);
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
        try {
            return Optional.ofNullable(
                    jdbcOperations.queryForObject(
                            "SELECT * FROM BOOKS WHERE id = :id",
                            params,
                            new BookRowMapper(jdbcOperations)
                    )
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private boolean genreCheckInDB(long id) {
        Integer count = jdbcOperations.getJdbcOperations().queryForObject(
                "SELECT count(*) FROM GENRE WHERE id = " + id,
                Integer.class
        );
        return count > 0;
    }

    private boolean authorCheckInDB(long id) {
        Integer count = jdbcOperations.getJdbcOperations().queryForObject(
                "SELECT count(*) FROM AUTHOR WHERE id = " + id,
                Integer.class
        );
        return count > 0;
    }

    private void genreUpdate(Book book) {
        SqlParameterSource genreNamedParameters = new MapSqlParameterSource()
                .addValue("id", book.getGenre().getId())
                .addValue("name", book.getGenre().getName());
        jdbcOperations.update(
                "UPDATE GENRE SET name = :name WHERE id = :id",
                genreNamedParameters
        );
    }

    private void authorUpdate(Book book) {
        SqlParameterSource authorNamedParameters = new MapSqlParameterSource()
                .addValue("id", book.getAuthor().getId())
                .addValue("name", book.getAuthor().getName());
        jdbcOperations.update(
                "UPDATE AUTHOR SET name = :name WHERE id = :id",
                authorNamedParameters
        );
    }

    private void genreSave(String genreName) {
        final HashMap<String, Object> genreParams = new HashMap<>(1);
        genreParams.put("name", genreName);
        jdbcOperations.update(
                "INSERT INTO GENRE (id, name) VALUES (default, :name)",
                genreParams
        );
    }

    private void authorSave(String authorName) {
        final HashMap<String, Object> authorParams = new HashMap<>(1);
        authorParams.put("name", authorName);
        jdbcOperations.update(
                "INSERT INTO AUTHOR (id, name) VALUES (default, :name)",
                authorParams
        );
    }

    private Long getAuthorIdFromDB(String authorName) {
        SqlParameterSource authorNamedParameters = new MapSqlParameterSource()
                .addValue("name", authorName);
        return jdbcOperations.queryForObject(
                "SELECT id FROM AUTHOR WHERE name = :name",
                authorNamedParameters, Long.class
        );
    }

    private Long getGenreIdFromDB(String genreName) {
        SqlParameterSource genreNamedParameters = new MapSqlParameterSource()
                .addValue("name", genreName);
        return jdbcOperations.queryForObject(
                "SELECT id FROM GENRE WHERE name = :name",
                genreNamedParameters, Long.class
        );
    }

    /**
     * Класс BookRowMapper позволяет получить значения полей экземпляра класса Book из БД через ResultSet
     */
    private static class BookRowMapper implements RowMapper<Book> {

        private final NamedParameterJdbcOperations jdbcOperations;

        private BookRowMapper(NamedParameterJdbcOperations jdbcOperations) {
            this.jdbcOperations = jdbcOperations;
        }

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            if(rs.wasNull()) return null;

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
