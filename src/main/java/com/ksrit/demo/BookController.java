package com.ksrit.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final Bookrepo repo;

    public BookController(Bookrepo repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", repo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book1());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book1 book) {
        repo.save(book);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book1 book = repo.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book1 book) {
        book.setId(id);
        repo.save(book);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
