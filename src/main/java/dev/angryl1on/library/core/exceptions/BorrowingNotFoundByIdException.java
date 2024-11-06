package dev.angryl1on.library.core.exceptions;

import java.util.UUID;

public class BorrowingNotFoundByIdException extends RuntimeException {
    public BorrowingNotFoundByIdException(UUID userId, UUID bookId) {
        super("No active borrowing found for userId: " + userId + " and bookId: " + bookId + ".");
    }
}
