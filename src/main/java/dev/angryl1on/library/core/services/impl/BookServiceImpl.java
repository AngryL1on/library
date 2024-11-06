package dev.angryl1on.library.core.services.impl;

import dev.angryl1on.library.core.exceptions.LibraryNotFoundException;
import dev.angryl1on.library.core.models.dtos.BookDTO;
import dev.angryl1on.library.core.exceptions.BookNotFoundException;
import dev.angryl1on.library.core.models.entity.Book;
import dev.angryl1on.library.core.models.entity.Library;
import dev.angryl1on.library.core.repositories.BookRepository;
import dev.angryl1on.library.core.repositories.LibraryRepository;
import dev.angryl1on.library.core.services.BookService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, LibraryRepository libraryRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book savedBook = bookRepository.save(book);

        return modelMapper.map(savedBook, BookDTO.class);
    }

    @Override
    public BookDTO getBookById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        List<Book> books = bookRepository.findByAvailable(true);
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

        return modelMapper.map(book, BookDTO.class);
    }


    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
