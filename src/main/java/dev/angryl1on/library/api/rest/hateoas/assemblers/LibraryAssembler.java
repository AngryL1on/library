package dev.angryl1on.library.api.rest.hateoas.assemblers;

import dev.angryl1on.library.api.rest.controllers.LibraryController;
import dev.angryl1on.libraryapi.models.dtos.LibraryDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LibraryAssembler extends RepresentationModelAssemblerSupport<LibraryDTO, LibraryDTO> {

    public LibraryAssembler() {
        super(LibraryController.class, LibraryDTO.class);
    }

    @Override
    public LibraryDTO toModel(LibraryDTO library) {
        LibraryDTO libraryDTO = instantiateModel(library);
        libraryDTO.setId(library.getId());
        libraryDTO.setName(library.getName());
        libraryDTO.setAddress(library.getAddress());

        libraryDTO.add(linkTo(methodOn(LibraryController.class).getLibraryById(library.getId())).withSelfRel());
        libraryDTO.add(linkTo(methodOn(LibraryController.class).getAllLibraries()).withRel("libraries"));

        return libraryDTO;
    }
}
