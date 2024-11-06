package dev.angryl1on.library.core.services;

import dev.angryl1on.library.core.models.dtos.BorrowingDTO;

import java.util.List;
import java.util.UUID;

public interface BorrowingService {
    BorrowingDTO borrowBook(UUID userId, UUID bookId);
    void returnBook(UUID userId, UUID bookId);
    BorrowingDTO getBorrowingById(UUID id);
    List<BorrowingDTO> getAllBorrowings();
    List<BorrowingDTO> getBorrowingsByUser(UUID userId);
    List<BorrowingDTO> getActiveBorrowingsByUser(UUID userId);
    List<BorrowingDTO> getActiveBorrowingsByBook(UUID bookId);
}
