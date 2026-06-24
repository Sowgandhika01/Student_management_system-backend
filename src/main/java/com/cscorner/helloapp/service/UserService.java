package com.cscorner.helloapp.service;

import org.springframework.stereotype.Service;
import com.cscorner.helloapp.repository.UserRepository;
import com.cscorner.helloapp.model.User;
import com.cscorner.helloapp.dto.UserRequestDTO;
import com.cscorner.helloapp.dto.UserResponseDTO;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {

        // DTO → Entity
        User user = new User();
        user.setName(dto.name);
        user.setEmail(dto.email);
        user.setPassword(dto.password);

        // save to database
        User saved = repo.save(user);

        // Entity → DTO (hide password)
        return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }

    public UserResponseDTO getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
