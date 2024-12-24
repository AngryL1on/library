package dev.angryl1on.library.core.services.impl;

import dev.angryl1on.libraryapi.models.dtos.LibraryDTO;
import dev.angryl1on.library.core.exceptions.LibraryNotFoundException;
import dev.angryl1on.library.core.models.entity.Library;
import dev.angryl1on.library.core.repositories.LibraryRepository;
import dev.angryl1on.library.core.services.LibraryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, ModelMapper modelMapper) {
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LibraryDTO addLibrary(LibraryDTO libraryDTO) {
        Library library = modelMapper.map(libraryDTO, Library.class);
        Library savedLibrary = libraryRepository.save(library);
        return modelMapper.map(savedLibrary, LibraryDTO.class);
    }

    @Override
    public LibraryDTO getLibraryById(UUID id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException(id));
        return modelMapper.map(library, LibraryDTO.class);
    }

    @Override
    public List<LibraryDTO> getAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        return libraries.stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryDTO> getLibrariesByName(String name) {
        List<Library> libraries = libraryRepository.findByName(name);
        return libraries.stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryDTO> getLibrariesByAddress(String address) {
        List<Library> libraries = libraryRepository.findByAddress(address);
        return libraries.stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLibrary(UUID id) {
        libraryRepository.deleteById(id);
    }
}
