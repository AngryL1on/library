package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.*;
import dev.angryl1on.libraryapi.models.dtos.LibraryDTO;
import dev.angryl1on.library.core.models.dtos.register.LibraryInput;
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

    @DgsMutation
    public LibraryDTO addLibrary(@InputArgument(name = "library") LibraryInput libraryInput) {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName(libraryInput.getName());
        libraryDTO.setAddress(libraryInput.getAddress());

       libraryService.addLibrary(libraryDTO);

       return libraryDTO;
    }

    @DgsQuery(field = "allLibraries")
    public List<LibraryDTO> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @DgsQuery(field = "libraryById")
    public LibraryDTO getLibraryById(@InputArgument(name = "id") UUID id) {
        return libraryService.getLibraryById(id);
    }

    @DgsQuery(field = "librariesByName")
    public List<LibraryDTO> getLibrariesByName(@InputArgument(name = "name") String name) {
        return libraryService.getLibrariesByName(name);
    }

    @DgsQuery(field = "librariesByAddress")
    public List<LibraryDTO> getLibrariesByAddress(@InputArgument(name = "address") String address) {
        return libraryService.getLibrariesByAddress(address);
    }

    @DgsMutation
    public Boolean deleteLibrary(@InputArgument(name = "id") UUID id) {
        libraryService.deleteLibrary(id);
        return true;
    }
}
