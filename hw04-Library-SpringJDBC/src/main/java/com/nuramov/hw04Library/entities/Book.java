package com.nuramov.hw04Library.entities;

import java.util.List;

public class Book {

    private Long id;
    private String title;
    private List<String> authorLastnames;
    private String genreName;

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

    public List<String> getAuthorLastnames() {
        return authorLastnames;
    }

    public void setAuthorLastnames(List<String> authorLastnames) {
        this.authorLastnames = authorLastnames;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorLastnames=" + authorLastnames +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
