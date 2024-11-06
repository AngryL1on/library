package dev.angryl1on.library.core.models.entity;

import dev.angryl1on.library.core.models.entity.enums.UserRoles;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends Base {
    private String name;
    private String email;
    private UserRoles role;
    private List<Borrowing> borrowings;

    /**
     * Protected constructor for JPA.
     */
    protected User() { /* do nothing */ }

    public User(String name, String email, UserRoles role, List<Borrowing> borrowings) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.borrowings = borrowings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
