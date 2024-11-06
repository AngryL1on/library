package dev.angryl1on.library.core.exceptions;

import java.util.UUID;

public class LibraryNotFoundException extends RuntimeException {
    public LibraryNotFoundException(UUID libraryId) {
        super("Library with ID " + libraryId + " not found.");
    }
}
