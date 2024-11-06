package dev.angryl1on.library.core.exceptions;

import java.util.UUID;

public class BorrowingNotFoundException extends RuntimeException {
    public BorrowingNotFoundException(UUID bookId) {
        super("No active borrowing found for book with ID " + bookId + ".");
    }
}
