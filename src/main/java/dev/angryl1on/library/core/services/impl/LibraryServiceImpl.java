package dev.angryl1on.library.core.services.impl;

import dev.angryl1on.libraryapi.models.dtos.LibraryDTO;
import dev.angryl1on.library.core.exceptions.LibraryNotFoundException;
import dev.angryl1on.library.core.models.entity.Library;
import dev.angryl1on.library.core.repositories.LibraryRepository;
import dev.angryl1on.library.core.services.LibraryService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, ModelMapper modelMapper, RabbitTemplate rabbitTemplate) {
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public LibraryDTO addLibrary(LibraryDTO libraryDTO) {
        Library library = modelMapper.map(libraryDTO, Library.class);
        Library savedLibrary = libraryRepository.save(library);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Library added: " + savedLibrary.getId());
        rabbitTemplate.convertAndSend("new_libraries_queue",
                "New library added: {\"id\": \"" + savedLibrary.getId() + "\", \"name\": \"" + savedLibrary.getName() + "\"}");

        return modelMapper.map(savedLibrary, LibraryDTO.class);
    }

    @Override
    public LibraryDTO getLibraryById(UUID id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException(id));

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched library by ID: " + id);

        return modelMapper.map(library, LibraryDTO.class);
    }

    @Override
    public List<LibraryDTO> getAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched all libraries");

        return libraries.stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryDTO> getLibrariesByName(String name) {
        List<Library> libraries = libraryRepository.findByName(name);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched libraries by name: " + name);

        return libraries.stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryDTO> getLibrariesByAddress(String address) {
        List<Library> libraries = libraryRepository.findByAddress(address);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched libraries by address: " + address);

        return libraries.stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLibrary(UUID id) {
        libraryRepository.deleteById(id);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Deleted library with ID: " + id);
    }
}
