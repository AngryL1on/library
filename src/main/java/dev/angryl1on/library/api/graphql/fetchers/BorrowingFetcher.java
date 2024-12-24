package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.*;
import dev.angryl1on.libraryapi.models.dtos.BorrowingDTO;
import dev.angryl1on.library.core.services.impl.BorrowingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class BorrowingFetcher {
    private final BorrowingServiceImpl borrowingService;

    @Autowired
    public BorrowingFetcher(BorrowingServiceImpl borrowingService) {
        this.borrowingService = borrowingService;
    }

    @DgsMutation(field = "borrowBook")
    public BorrowingDTO borrowBook(@InputArgument(name = "userId") UUID userId, @InputArgument(name = "bookId") UUID bookId) {
        return borrowingService.borrowBook(userId, bookId);
    }

    @DgsQuery(field = "allBorrowings")
    public List<BorrowingDTO> getAllBorrowings() {
        return borrowingService.getAllBorrowings();
    }

    @DgsQuery(field = "borrowingById")
    public BorrowingDTO getBorrowingById(@InputArgument(name = "id") UUID id) {
        return borrowingService.getBorrowingById(id);
    }

    @DgsQuery(field = "borrowingsByUser")
    public List<BorrowingDTO> getBorrowingByUserId(@InputArgument(name = "userId") UUID userId) {
        return borrowingService.getBorrowingsByUser(userId);
    }

    @DgsQuery(field = "borrowingsByBook")
    public List<BorrowingDTO> getBorrowingByBookId(@InputArgument(name = "bookId") UUID bookId) {
        return borrowingService.getBorrowingsByUser(bookId);
    }

    @DgsQuery(field = "borrowingsByActiveByUser")
    public List<BorrowingDTO> getBorrowingByBorrowDate(@InputArgument(name = "userId") UUID userId) {
        return borrowingService.getActiveBorrowingsByUser(userId);
    }

    @DgsMutation(field = "returnBook")
    public Boolean returnBook(@InputArgument(name = "userId") UUID userId, @InputArgument(name = "bookId") UUID bookId) {
        borrowingService.returnBook(userId, bookId);
        return true;
    }
}
