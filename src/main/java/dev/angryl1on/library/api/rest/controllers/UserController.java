package dev.angryl1on.library.api.rest.controllers;

import dev.angryl1on.library.api.rest.hateoas.assemblers.UserAssembler;
import dev.angryl1on.library.core.models.dtos.UserDTO;
import dev.angryl1on.library.core.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserAssembler userAssembler;

    @Autowired
    public UserController(UserServiceImpl userService, UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
    }

    @GetMapping("/find")
    public ResponseEntity<UserDTO> getUserById(@RequestParam UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userAssembler.toModel(userDTO));
    }

    @GetMapping
    public CollectionModel<UserDTO> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return userAssembler.toCollectionModel(users);
    }

    @PutMapping("/edit")
    public ResponseEntity<UserDTO> updateUser(@RequestParam UUID id, @RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(userAssembler.toModel(newUser));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
