package dev.angryl1on.library.api.rest.hateoas.assemblers;

import dev.angryl1on.library.api.rest.controllers.BookController;
import dev.angryl1on.library.api.rest.controllers.LibraryController;
import dev.angryl1on.libraryapi.models.dtos.BookDTO;
import dev.angryl1on.libraryapi.models.dtos.LibraryDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookAssembler extends RepresentationModelAssemblerSupport<BookDTO, BookDTO> {

    public BookAssembler() {
        super(BookController.class, BookDTO.class);
    }

    @Override
    public BookDTO toModel(BookDTO book) {
        BookDTO bookDTO = instantiateModel(book);
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setAvailable(book.isAvailable());

        bookDTO.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
        bookDTO.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));

        if (book.getLibrary() != null) {
            bookDTO.setLibrary(new LibraryDTO(book.getLibrary().getId(), book.getLibrary().getName(), book.getLibrary().getAddress()));
            bookDTO.add(linkTo(methodOn(LibraryController.class).getLibraryById(book.getLibrary().getId())).withRel("library"));
        }

        return bookDTO;
    }
}
