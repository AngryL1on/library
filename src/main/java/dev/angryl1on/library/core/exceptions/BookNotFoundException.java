package dev.angryl1on.library.core.exceptions;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID bookId) {
        super("Book with ID " + bookId + " not found.");
    }
}
