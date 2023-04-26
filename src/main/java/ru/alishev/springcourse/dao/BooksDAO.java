package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), bookId);
    }

    public Optional<Person> getBookOwner(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                "WHERE Book.id = ?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", bookId);
    }

    public void assignBook(int bookId, Person owner) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", owner.getId(), bookId);
    }

    public void returnBook(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", bookId);
    }
}
