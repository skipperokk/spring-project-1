package ru.alishev.springcourse.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "The book name should not be empty!")
    @Size(min = 2, max = 60, message = "The book name should consist of at least 2 and a maximum of 60 letters!")
    private String name;
    @NotEmpty(message = "Author should not be empty!")
    @Size(min = 2, max = 60, message = "The author should consist of at least 2 and a maximum of 60 letters!")
    private String author;
    @NotEmpty(message = "Year should not be empty!")
    private String year;

    public Book() {
    }

    public Book(String name, String author, String year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
