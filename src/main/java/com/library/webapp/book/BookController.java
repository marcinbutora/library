package com.library.webapp.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/bytitle/{title}")
    public List<Book> getBookByName(@PathVariable("title") String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable("id") Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book save = bookService.save(book);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
