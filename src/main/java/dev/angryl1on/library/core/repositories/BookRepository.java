package dev.angryl1on.library.core.repositories;

import dev.angryl1on.library.core.models.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    /**
     * Finds books by their title.
     *
     * @param title the title or part of the title of the book to search for.
     * @return a list of books that match the search criteria.
     */
    List<Book> findByTitle(String title);

    /**
     * Finds books by their author.
     *
     * @param author the name or part of the name of the author to search for.
     * @return a list of books written by the specified author.
     */
    List<Book> findByAuthor(String author);

    /**
     * Finds books that are available for borrowing.
     *
     * @param available boolean flag indicating availability.
     * @return a list of available books.
     */
    List<Book> findByAvailable(boolean available);
}
