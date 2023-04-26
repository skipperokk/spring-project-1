package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BooksDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int bookId, @ModelAttribute("person") Person emptyPerson, Model model) {
        model.addAttribute("book", booksDAO.show(bookId).get());
        Optional<Person> bookOwner = booksDAO.getBookOwner(bookId);
        if (bookOwner.isPresent()) {
            model.addAttribute("bookOwner", bookOwner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        booksDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int bookId) {
        model.addAttribute("book", booksDAO.show(bookId).get());
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, BindingResult bindingResult, @PathVariable("id") int bookId) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksDAO.update(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int bookId) {
        booksDAO.delete(bookId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assignBook")
    public String assignBook(@PathVariable("id") int bookId, @ModelAttribute("person") Person owner) {
        booksDAO.assignBook(bookId, owner);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/returnBook")
    public String returnBook(@PathVariable("id") int bookId) {
        booksDAO.returnBook(bookId);
        return "redirect:/books/" + bookId;
    }
}
