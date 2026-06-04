package com.sanket.novelnest.controller;

import com.sanket.novelnest.entity.Book;
import com.sanket.novelnest.repository.BookRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String test() {
        return "Running successfully";
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping("/all")
    public List<Book> getAll() {
        return repository.findAll();
    }

    @PostMapping("/upload")
    public String uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "uploads/";
        Path path = Paths.get(uploadDir + file.getOriginalFilename());
        Files.write(path, file.getBytes());
        return "File uploaded successfully";
    }

    @PostMapping("/upload-book")
    public Book uploadBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam MultipartFile file)
            throws IOException {

        Files.createDirectories(Paths.get("Uploads"));

        String fileName = file.getOriginalFilename();

        Path path = Paths.get("Uploads", fileName);

        file.transferTo(path);

        Book book = new Book();

        book.setTitle(title);
        book.setAuthor(author);
        book.setPdfPath(fileName);

        return repository.save(book);
    }
}
