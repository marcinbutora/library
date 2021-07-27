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
    public List<BookDTO> getAllBooks() {
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
    ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        BookDTO save = bookService.save(bookDTO);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

//    @PutMapping(value = "/{id}")
//    public Book updateBook(@RequestBody Book book, @PathVariable("id") Long id) {
//        return bookService.findById(id).map(b -> {
//            b.setTitle(book.getTitle());
//            b.setDescription(book.getDescription());
//            b.setIsbn(book.getIsbn());
//            return bookService.save(b);
//        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
//    }

    // TODO: update


//    public BookDTO update(@RequestBody Book book, @PathVariable("id") Long id) {
//        return BookConverter.entityToDTO(book.getId());
//    }

//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
//        return bookService.findById(id).map(b -> {
//            bookService.delete(b);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
//    }
}
