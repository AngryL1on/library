package dev.angryl1on.library.api.rest.controllers;

import dev.angryl1on.library.api.rest.hateoas.assemblers.BorrowingAssembler;
import dev.angryl1on.library.core.models.dtos.BorrowingDTO;
import dev.angryl1on.library.core.services.impl.BorrowingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    private final BorrowingServiceImpl borrowingService;
    private final BorrowingAssembler borrowingAssembler;

    @Autowired
    public BorrowingController(BorrowingServiceImpl borrowingService, BorrowingAssembler borrowingAssembler) {
        this.borrowingService = borrowingService;
        this.borrowingAssembler = borrowingAssembler;
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingDTO> borrowBook(@RequestParam UUID userId, @RequestParam UUID bookId) {
        BorrowingDTO borrowingDTO = borrowingService.borrowBook(userId, bookId);
        return ResponseEntity.created(URI.create("api/borrowings/" + borrowingDTO.getId())).body(borrowingAssembler.toModel(borrowingDTO));
    }

    @PutMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestParam UUID userId, @RequestParam UUID bookId) {
        borrowingService.returnBook(userId, bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find")
    public ResponseEntity<BorrowingDTO> getBorrowingById(@RequestParam UUID id) {
        BorrowingDTO borrowingDTO = borrowingService.getBorrowingById(id);
        return ResponseEntity.ok(borrowingAssembler.toModel(borrowingDTO));
    }

    @GetMapping
    public CollectionModel<BorrowingDTO> getAllBorrowings() {
        List<BorrowingDTO> borrowings = borrowingService.getAllBorrowings();
        return borrowingAssembler.toCollectionModel(borrowings);
    }

    @GetMapping("/find/user")
    public CollectionModel<BorrowingDTO> getBorrowingsByUser(@RequestParam UUID userId) {
        List<BorrowingDTO> borrowings = borrowingService.getBorrowingsByUser(userId);
        return borrowingAssembler.toCollectionModel(borrowings);
    }

    @GetMapping("/user/active")
    public CollectionModel<BorrowingDTO> getActiveBorrowingsByUser(@RequestParam UUID userId) {
        List<BorrowingDTO> borrowings = borrowingService.getActiveBorrowingsByUser(userId);
        return borrowingAssembler.toCollectionModel(borrowings);
    }

    @GetMapping("/book/active")
    public CollectionModel<BorrowingDTO> getActiveBorrowingsByBook(@RequestParam UUID bookId) {
        List<BorrowingDTO> borrowings = borrowingService.getActiveBorrowingsByBook(bookId);
        return borrowingAssembler.toCollectionModel(borrowings);
    }
}
