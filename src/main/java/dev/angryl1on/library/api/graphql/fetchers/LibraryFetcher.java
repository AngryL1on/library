package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import dev.angryl1on.library.core.models.dtos.LibraryDTO;
import dev.angryl1on.library.core.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class LibraryFetcher {
    private final LibraryService libraryService;

    @Autowired
    public LibraryFetcher(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    // Fetcher для получения библиотеки по ID
    @DgsQuery(field = "libraryById")
    public LibraryDTO getLibraryById(DgsDataFetchingEnvironment dfe) {
        UUID id = dfe.getArgument("id");
        return libraryService.getLibraryById(id);
    }

    // Fetcher для получения всех библиотек
    @DgsQuery(field = "allLibraries")
    public List<LibraryDTO> getAllLibraries() {
        return libraryService.getAllLibraries();
    }
}

