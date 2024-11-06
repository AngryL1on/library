package dev.angryl1on.library.core.services;

import dev.angryl1on.library.core.models.dtos.UserDTO;
import dev.angryl1on.library.core.exceptions.UserNotFoundException;
import dev.angryl1on.library.core.models.entity.enums.UserRoles;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing user operations.
 * This interface defines the contract for user-related operations such as
 * registration, retrieval, update, and deletion of users.
 */
public interface UserService {

    /**
     * Registers a new user in the system.
     *
     * @param userDTO The data transfer object containing user information to be registered.
     * @return A UserDTO object representing the newly registered user, including any
     *         system-generated fields such as ID.
     */
    UserDTO registerUser(UserDTO userDTO);

    /**
     * Retrieves all users from the system.
     *
     * @return A List of UserDTO objects representing all users in the system.
     */
    List<UserDTO> getAllUsers();

    /**
     * Retrieves a specific user by their unique identifier.
     *
     * @param id The UUID of the user to retrieve.
     * @return A UserDTO object representing the user with the specified ID.
     * @throws UserNotFoundException if no user is found with the given ID.
     */
    UserDTO getUserById(UUID id);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return A UserDTO object representing the user with the specified email.
     * @throws UserNotFoundException if no user is found with the given email.
     */
    UserDTO getUserByEmail(String email);

    /**
     * Retrieves all users with a specific role.
     *
     * @param role The role to filter users by.
     * @return A List of UserDTO objects representing users with the specified role.
     */
    List<UserDTO> getUsersByRole(UserRoles role);

    /**
     * Updates the information of an existing user.
     *
     * @param id The UUID of the user to update.
     * @param userDTO The UserDTO object containing the updated user information.
     * @return A UserDTO object representing the updated user.
     * @throws UserNotFoundException if no user is found with the given ID.
     */
    UserDTO updateUser(UUID id, UserDTO userDTO);

    /**
     * Deletes a user from the system.
     *
     * @param id The UUID of the user to delete.
     * @throws UserNotFoundException if no user is found with the given ID.
     */
    void deleteUser(UUID id);
}
