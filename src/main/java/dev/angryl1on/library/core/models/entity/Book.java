package dev.angryl1on.library.core.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book extends Base {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean available;

    private Library library;

    /**
     * Protected constructor for JPA.
     */
    protected Book() { /* do nothing */ }

    public Book(String title, String author, String isbn, int publicationYear, boolean available, Library library) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = available;
        this.library = library;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @ManyToOne
    @JoinColumn(name = "library_id")
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
