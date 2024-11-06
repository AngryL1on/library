package dev.angryl1on.library.core.models.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.UUID;

public class LibraryDTO extends RepresentationModel<LibraryDTO> {
    private UUID id;
    private String name;
    private String address;
    private List<BookDTO> books;

    public LibraryDTO() { /* Do nothing */ }

    public LibraryDTO(UUID id, String name, String address, List<BookDTO> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public LibraryDTO(UUID id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonBackReference
    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "LibraryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", books=" + books +
                '}';
    }
}
