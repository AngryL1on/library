package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import dev.angryl1on.library.core.models.dtos.BookDTO;
import dev.angryl1on.library.core.models.dtos.UserDTO;
import dev.angryl1on.library.core.models.entity.enums.UserRoles;
import dev.angryl1on.library.core.services.BookService;
import dev.angryl1on.library.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class MutationFetcher {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public MutationFetcher(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    // Mutation для добавления книги
    @DgsMutation(field = "addBook")
    public BookDTO addBook(String title, String author, String isbn, int publicationYear, boolean available) {
        return bookService.addBook(title, author, isbn, publicationYear, available);
    }

    // Mutation для регистрации пользователя
    @DgsMutation(field = "registerUser")
    public UserDTO registerUser(String name, String email, UserRoles role) {
        return userService.registerUser(name, email, role);
    }
}

