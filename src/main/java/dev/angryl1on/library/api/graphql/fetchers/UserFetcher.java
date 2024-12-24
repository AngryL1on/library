package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.*;
import dev.angryl1on.libraryapi.models.dtos.UserDTO;
import dev.angryl1on.library.core.models.dtos.register.UserInput;
import dev.angryl1on.libraryapi.models.entity.enums.UserRoles;
import dev.angryl1on.library.core.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class UserFetcher {

    private final UserServiceImpl userService;

    @Autowired
    public UserFetcher(UserServiceImpl userService) {
        this.userService = userService;
    }

    @DgsMutation
    public UserDTO registerUser(@InputArgument(name = "user") UserInput userInput) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userInput.getName());
        userDTO.setEmail(userInput.getEmail());
        userDTO.setPassword(userInput.getPassword());
        userDTO.setRole(userInput.getRole());

         userService.registerUser(userDTO);

        return userDTO;
    }

    @DgsQuery
    public List<UserDTO> allUsers() {
        return userService.getAllUsers();
    }

    @DgsQuery(field = "userById")
    public UserDTO getUserById(@InputArgument(name = "id") UUID id) {
        return userService.getUserById(id);
    }

    @DgsQuery(field = "userByEmail")
    public UserDTO getUserByEmail(@InputArgument(name = "email") String email) {
        return userService.getUserByEmail(email);
    }

    @DgsQuery(field = "usersByRole")
    public List<UserDTO> getUsersByRole(@InputArgument(name = "role") UserRoles role) {
        return userService.getUsersByRole(role);
    }

    @DgsMutation(field = "updateUser")
    public UserDTO updateUser(@InputArgument(name = "id") UUID id, @InputArgument(name = "user") UserDTO userDTO) {
        userDTO.setId(id);
        UserDTO updatedUser = userService.updateUser(id, userDTO);

        return new UserDTO(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getPassword(),
                updatedUser.getRole()
        );
    }

    @DgsMutation
    public Boolean deleteUser(@InputArgument(name = "id") UUID id) {
        userService.deleteUser(id);
        return true;
    }
}
