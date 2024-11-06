package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import dev.angryl1on.library.core.models.dtos.BorrowingDTO;
import dev.angryl1on.library.core.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class BorrowingFetcher {
    private final BorrowingService borrowingService;

    @Autowired
    public BorrowingFetcher(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    // Fetcher для получения записи о займе по ID
    @DgsQuery(field = "borrowingById")
    public BorrowingDTO getBorrowingById(DgsDataFetchingEnvironment dfe) {
        UUID id = dfe.getArgument("id");
        return borrowingService.getBorrowingById(id);
    }

    // Fetcher для получения всех записей о займах
    @DgsQuery(field = "allBorrowings")
    public List<BorrowingDTO> getAllBorrowings() {
        return borrowingService.getAllBorrowings();
    }
}

