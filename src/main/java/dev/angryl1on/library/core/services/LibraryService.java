package dev.angryl1on.library.core.services;

import dev.angryl1on.libraryapi.models.dtos.LibraryDTO;

import java.util.List;
import java.util.UUID;

public interface LibraryService {
    LibraryDTO addLibrary(LibraryDTO libraryDTO);
    LibraryDTO getLibraryById(UUID id);
    List<LibraryDTO> getAllLibraries();
    List<LibraryDTO> getLibrariesByName(String name);
    List<LibraryDTO> getLibrariesByAddress(String address);
    void deleteLibrary(UUID id);
}
