package dev.angryl1on.library.core.services;

import dev.angryl1on.libraryapi.models.dtos.BorrowingDTO;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing borrowings in the library system.
 * Provides methods to borrow, return, and retrieve borrowing information.
 */
public interface BorrowingService {

    /**
     * Allows a user to borrow a book from the library.
     *
     * @param userId the UUID of the user borrowing the book
     * @param bookId the UUID of the book being borrowed
     * @return a {@link BorrowingDTO} containing the details of the borrowing transaction
     */
    BorrowingDTO borrowBook(UUID userId, UUID bookId);

    /**
     * Allows a user to return a borrowed book to the library.
     *
     * @param userId the UUID of the user returning the book
     * @param bookId the UUID of the book being returned
     */
    void returnBook(UUID userId, UUID bookId);

    /**
     * Retrieves a borrowing record by its unique identifier.
     *
     * @param id the UUID of the borrowing record
     * @return a {@link BorrowingDTO} containing the borrowing details
     */
    BorrowingDTO getBorrowingById(UUID id);

    /**
     * Retrieves all borrowing records.
     *
     * @return a list of {@link BorrowingDTO} containing details of all borrowings
     */
    List<BorrowingDTO> getAllBorrowings();

    /**
     * Retrieves all borrowings associated with a specific user.
     *
     * @param userId the UUID of the user whose borrowings are to be retrieved
     * @return a list of {@link BorrowingDTO} containing the borrowings of the specified user
     */
    List<BorrowingDTO> getBorrowingsByUser(UUID userId);

    /**
     * Retrieves all active borrowings associated with a specific user.
     * Active borrowings are those that have not yet been returned.
     *
     * @param userId the UUID of the user whose active borrowings are to be retrieved
     * @return a list of {@link BorrowingDTO} containing the active borrowings of the specified user
     */
    List<BorrowingDTO> getActiveBorrowingsByUser(UUID userId);

    /**
     * Retrieves all active borrowings associated with a specific book.
     * Active borrowings are those that have not yet been returned.
     *
     * @param bookId the UUID of the book whose active borrowings are to be retrieved
     * @return a list of {@link BorrowingDTO} containing the active borrowings of the specified book
     */
    List<BorrowingDTO> getActiveBorrowingsByBook(UUID bookId);
}
