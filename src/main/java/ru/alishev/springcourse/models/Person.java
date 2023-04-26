package ru.alishev.springcourse.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 100, message = "The name should consist of at least 2 and a maximum of 60 letters!")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bday;

    public Person() {
    }

    public Person(String name, Date bday) {
        this.name = name;
        this.bday = bday;
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

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }
}
