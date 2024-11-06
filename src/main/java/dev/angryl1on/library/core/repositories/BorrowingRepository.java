package dev.angryl1on.library.core.repositories;

import dev.angryl1on.library.core.models.entity.Book;
import dev.angryl1on.library.core.models.entity.Borrowing;
import dev.angryl1on.library.core.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, UUID> {
    /**
     * Finds all borrowings (leases) by a specific user.
     *
     * @param user the user whose borrowings are being searched.
     * @return a list of borrowings by the given user.
     */
    List<Borrowing> findByUser(User user);

    /**
     * Finds all active borrowings (i.e., those without a return date) for a specific user.
     *
     * @param user the user whose active borrowings are being searched.
     * @return a list of active borrowings by the given user.
     */
    @Query("SELECT b FROM Borrowing b WHERE b.user = :user AND b.returnDate IS NULL")
    List<Borrowing> findActiveBorrowingsByUser(@Param("user") User user);

    /**
     * Finds all active borrowings (i.e., those without a return date) for a specific book.
     *
     * @param book the book whose active borrowings are being searched.
     * @return a list of active borrowings for the given book.
     */
    @Query("SELECT b FROM Borrowing b WHERE b.book = :book AND b.returnDate IS NULL")
    List<Borrowing> findActiveBorrowingsByBook(@Param("book") Book book);

    Optional<Borrowing> findByUserIdAndBookId(UUID userId, UUID bookId);
}
