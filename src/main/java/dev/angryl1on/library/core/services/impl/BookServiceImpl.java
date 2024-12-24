package dev.angryl1on.library.core.services.impl;

import dev.angryl1on.library.core.configs.RabbitMQConfig;
import dev.angryl1on.library.core.exceptions.LibraryNotFoundException;
import dev.angryl1on.library.core.exceptions.BookNotFoundException;
import dev.angryl1on.library.core.models.entity.Book;
import dev.angryl1on.library.core.models.entity.Library;
import dev.angryl1on.library.core.repositories.BookRepository;
import dev.angryl1on.library.core.repositories.LibraryRepository;
import dev.angryl1on.library.core.services.BookService;
import dev.angryl1on.libraryapi.models.dtos.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, LibraryRepository libraryRepository, ModelMapper modelMapper, RabbitTemplate rabbitTemplate) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book savedBook = bookRepository.save(book);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Added new book with ID: " + savedBook.getId());
        rabbitTemplate.convertAndSend(RabbitMQConfig.NEW_BOOKS_QUEUE,
                "New book added: {\"id\": \"" + savedBook.getId() + "\", \"title\": \"" + savedBook.getTitle() + "\"}");

        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public BookDTO getBookById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched book with ID: " + id);

        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched all books");

        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched books with title: " + title);

        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched books by author: " + author);

        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        List<Book> books = bookRepository.findByAvailable(true);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched available books");

        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    public BookDTO assignBookToLibrary(UUID bookId, UUID libraryId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new LibraryNotFoundException(libraryId));

        book.setLibrary(library);
        bookRepository.save(book);

        rabbitTemplate.convertAndSend("audit_logs_queue",
                "Assigned book with ID: " + bookId + " to library with ID: " + libraryId);

        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Deleted book with ID: " + id);
    }
}
