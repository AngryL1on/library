package dev.angryl1on.library.api.rest.hateoas.assemblers;

import dev.angryl1on.library.api.rest.controllers.UserController;
import dev.angryl1on.libraryapi.models.dtos.UserDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserDTO> {

    public UserAssembler() {
        super(UserController.class, UserDTO.class);
    }

    @Override
    public UserDTO toModel(UserDTO user) {
        UserDTO userDTO = instantiateModel(user);
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        userDTO.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
        userDTO.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));

        return userDTO;
    }
}
