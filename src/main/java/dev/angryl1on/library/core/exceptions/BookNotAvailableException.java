package dev.angryl1on.library.core.exceptions;

import java.util.UUID;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(UUID bookId) {
        super("Book with ID " + bookId + " is not available for borrowing.");
    }
}
