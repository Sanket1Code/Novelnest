package com.sanket.novelnest.controller;

import com.sanket.novelnest.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ViewController {

    private final BookRepository repository;

    @GetMapping("/books-ui")
    public String books(Model model) {

        model.addAttribute("books", repository.findAll());

        return "books";
    }
}