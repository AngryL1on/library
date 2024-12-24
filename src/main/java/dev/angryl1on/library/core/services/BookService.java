package dev.angryl1on.library.core.services;

import dev.angryl1on.libraryapi.models.dtos.BookDTO;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing books in the library system.
 */
public interface BookService {

    /**
     * Adds a new book to the library.
     *
     * @param bookDTO the data transfer object containing the book details.
     * @return the added book as a {@link BookDTO}.
     */
    BookDTO addBook(BookDTO bookDTO);

    /**
     * Retrieves a book by its unique identifier.
     *
     * @param id the unique identifier of the book (UUID).
     * @return the found book as a {@link BookDTO}, or null if not found.
     */
    BookDTO getBookById(UUID id);

    /**
     * Retrieves a list of all books in the library.
     *
     * @return a list of all books as {@link BookDTO}.
     */
    List<BookDTO> getAllBooks();

    /**
     * Retrieves a list of books that match the given title.
     *
     * @param title the title of the book(s) to search for.
     * @return a list of books that match the title as {@link BookDTO}.
     */
    List<BookDTO> getBooksByTitle(String title);

    /**
     * Retrieves a list of books by the given author.
     *
     * @param author the author of the book(s) to search for.
     * @return a list of books by the author as {@link BookDTO}.
     */
    List<BookDTO> getBooksByAuthor(String author);

    /**
     * Retrieves a list of books that are available for borrowing.
     *
     * @return a list of available books as {@link BookDTO}.
     */
    List<BookDTO> getAvailableBooks();

    BookDTO assignBookToLibrary(UUID bookId, UUID libraryId);

    /**
     * Deletes a book from the library by its unique identifier.
     *
     * @param id the unique identifier of the book (UUID).
     */
    void deleteBook(UUID id);
}
