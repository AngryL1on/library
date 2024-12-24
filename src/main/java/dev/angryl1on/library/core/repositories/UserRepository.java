package dev.angryl1on.library.core.repositories;

import dev.angryl1on.library.core.models.entity.User;
import dev.angryl1on.libraryapi.models.entity.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a user by their email.
     *
     * @param email the email address of the user.
     * @return an Optional containing the user if found, or empty if no user is found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds users by their role.
     *
     * @param role the role of the users (e.g., User, Librarian).
     * @return a list of users that match the given role.
     */
    List<User> findByRole(UserRoles role);
}
