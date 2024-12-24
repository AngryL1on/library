package dev.angryl1on.library.core.services.impl;

import dev.angryl1on.libraryapi.models.dtos.UserDTO;
import dev.angryl1on.library.core.exceptions.UserNotFoundException;
import dev.angryl1on.library.core.models.entity.User;
import dev.angryl1on.libraryapi.models.entity.enums.UserRoles;
import dev.angryl1on.library.core.repositories.UserRepository;
import dev.angryl1on.library.core.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);

        rabbitTemplate.convertAndSend("audit_logs_queue", "User registered: " + savedUser.getId());

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched all users");

        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched user by ID: " + id);

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with " + email + " not found"));

        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched user by email: " + email);

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUsersByRole(UserRoles role) {
        rabbitTemplate.convertAndSend("audit_logs_queue", "Fetched user by role: " + role);

        return userRepository.findByRole(role).stream().map((s) -> modelMapper.map(s, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        rabbitTemplate.convertAndSend("audit_logs_queue", "Updated user with ID: " + id);

        return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);

        rabbitTemplate.convertAndSend("audit_logs_queue", "Deleted user with ID: " + id);
    }
}
