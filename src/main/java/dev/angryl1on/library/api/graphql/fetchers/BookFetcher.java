package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.*;
import dev.angryl1on.libraryapi.models.dtos.BookDTO;
import dev.angryl1on.library.core.models.dtos.register.BookInput;
import dev.angryl1on.library.core.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class BookFetcher {
    private final BookService bookService;

    @Autowired
    public BookFetcher(BookService bookService) {
        this.bookService = bookService;
    }

    @DgsMutation
    public BookDTO addBook(@InputArgument(name = "book") BookInput submittedBook) {
        BookDTO bookDto = new BookDTO();
        bookDto.setTitle(submittedBook.getTitle());
        bookDto.setAuthor(submittedBook.getAuthor());
        bookDto.setPublicationYear(submittedBook.getPublicationYear());
        bookDto.setIsbn(submittedBook.getIsbn());
        bookDto.setAvailable(submittedBook.isAvailable());

        bookService.addBook(bookDto);

        return bookDto;
    }

    @DgsQuery(field = "allBooks")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DgsQuery(field = "bookById")
    public BookDTO getBookById(@InputArgument(name = "id") UUID id) {
        return bookService.getBookById(id);
    }

    @DgsQuery(field = "booksByAuthor")
    public List<BookDTO> getBooksByAuthor(@InputArgument(name = "author") String author) {
        return bookService.getBooksByAuthor(author);
    }

    @DgsQuery(field = "booksByTitle")
    public List<BookDTO> getBooksByTitle(@InputArgument(name = "title") String title) {
        return bookService.getBooksByTitle(title);
    }

    @DgsQuery(field = "booksByAvailability")
    public List<BookDTO> getBooksByAvailability() {
        return bookService.getAvailableBooks();
    }

    @DgsMutation
    public BookDTO assignBookToLibrary(@InputArgument(name = "bookId") UUID bookId, @InputArgument(name = "libraryId") UUID libraryId) {
        BookDTO assignedBook = bookService.assignBookToLibrary(bookId, libraryId);


        return new BookDTO(
                assignedBook.getId(),
                assignedBook.getTitle(),
                assignedBook.getAuthor(),
                assignedBook.getIsbn(),
                assignedBook.getPublicationYear(),
                assignedBook.isAvailable(),
                assignedBook.getLibraryId()
        );
    }

    @DgsMutation
    public Boolean deleteBook(@InputArgument(name = "id") UUID bookId) {
        bookService.deleteBook(bookId);
        return true;
    }
}
