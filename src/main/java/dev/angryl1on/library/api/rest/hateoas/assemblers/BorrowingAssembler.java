package dev.angryl1on.library.api.rest.hateoas.assemblers;

import dev.angryl1on.library.api.rest.controllers.BookController;
import dev.angryl1on.library.api.rest.controllers.BorrowingController;
import dev.angryl1on.library.api.rest.controllers.UserController;
import dev.angryl1on.libraryapi.models.dtos.BookDTO;
import dev.angryl1on.libraryapi.models.dtos.BorrowingDTO;
import dev.angryl1on.libraryapi.models.dtos.UserDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BorrowingAssembler extends RepresentationModelAssemblerSupport<BorrowingDTO, BorrowingDTO> {

    public BorrowingAssembler() {
        super(BorrowingController.class, BorrowingDTO.class);
    }

    @Override
    public BorrowingDTO toModel(BorrowingDTO borrowing) {
        BorrowingDTO borrowingDTO = instantiateModel(borrowing);
        borrowingDTO.setId(borrowing.getId());
        borrowingDTO.setBorrowDate(borrowing.getBorrowDate());
        borrowingDTO.setReturnDate(borrowing.getReturnDate());

        borrowingDTO.add(linkTo(methodOn(BorrowingController.class).getBorrowingById(borrowing.getId())).withSelfRel());

        if (borrowing.getUser() != null) {
            borrowingDTO.setUser(new UserDTO(borrowing.getUser().getId(), borrowing.getUser().getName(), borrowing.getUser().getEmail(), borrowing.getUser().getRole()));
            borrowingDTO.add(linkTo(methodOn(UserController.class).getUserById(borrowing.getUser().getId())).withRel("user"));
        }
        if (borrowing.getBook() != null) {
            borrowingDTO.setBook(new BookDTO(borrowing.getBook().getId(), borrowing.getBook().getTitle(), borrowing.getBook().getAuthor(), borrowing.getBook().getIsbn(), borrowing.getBook().getPublicationYear(), borrowing.getBook().isAvailable()));
            borrowingDTO.add(linkTo(methodOn(BookController.class).getBookById(borrowing.getBook().getId())).withRel("book"));
        }

        return borrowingDTO;
    }
}
