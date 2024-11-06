package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import dev.angryl1on.library.core.models.dtos.BookDTO;
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

    // Fetcher для получения книги по ID
    @DgsQuery(field = "bookById")
    public BookDTO getBookById(DgsDataFetchingEnvironment dfe) {
        UUID id = dfe.getArgument("id");
        return bookService.getBookById(id);
    }

    // Fetcher для получения всех книг
    @DgsQuery(field = "allBooks")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
