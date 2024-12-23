package dev.angryl1on.library.api.rest.controllers;

import dev.angryl1on.library.api.rest.hateoas.assemblers.BookAssembler;
import dev.angryl1on.library.core.models.dtos.BookDTO;
import dev.angryl1on.library.core.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final BookAssembler bookAssembler;

    @Autowired
    public BookController(BookService bookService, BookAssembler bookAssembler) {
        this.bookService = bookService;
        this.bookAssembler = bookAssembler;
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookDTO newBook = bookService.addBook(bookDTO);
        return ResponseEntity.created(URI.create("/api/books/" + newBook.getId())).body(bookAssembler.toModel(newBook));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable UUID id) {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookAssembler.toModel(bookDTO));
    }

    @GetMapping
    public CollectionModel<BookDTO> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return bookAssembler.toCollectionModel(books);
    }

    @GetMapping("/find/title")
    public CollectionModel<BookDTO> getBooksByTitle(@RequestParam String title) {
        List<BookDTO> books = bookService.getBooksByTitle(title);
        return bookAssembler.toCollectionModel(books);
    }

    @GetMapping("/find/author")
    public CollectionModel<BookDTO> getBooksByAuthor(@RequestParam String author) {
        List<BookDTO> books = bookService.getBooksByAuthor(author);
        return bookAssembler.toCollectionModel(books);
    }

    @GetMapping("/find/available")
    public CollectionModel<BookDTO> getAvailableBooks() {
        List<BookDTO> books = bookService.getAvailableBooks();
        return bookAssembler.toCollectionModel(books);
    }

    @PutMapping("/{bookId}/assign")
    public ResponseEntity<BookDTO> assignBookToLibrary(
            @PathVariable UUID bookId,
            @RequestParam UUID libraryId) {
        BookDTO updatedBook = bookService.assignBookToLibrary(bookId, libraryId);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestParam UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
