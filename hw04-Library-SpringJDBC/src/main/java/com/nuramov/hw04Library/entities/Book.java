package com.nuramov.hw04Library.entities;

import java.util.Objects;

public class Book {

    private Long id;
    private String title;
    private Author author;
    private Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId().equals(book.getId())
                && getTitle().equals(book.getTitle())
                && getAuthor().getId().equals(book.getAuthor().getId())
                && getAuthor().getName().equals(book.getAuthor().getName())
                && getGenre().getId().equals(book.getGenre().getId())
                && getGenre().getName().equals(book.getGenre().getName()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthor(), getGenre());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
