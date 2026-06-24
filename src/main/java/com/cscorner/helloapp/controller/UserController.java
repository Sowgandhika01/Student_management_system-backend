package com.cscorner.helloapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.cscorner.helloapp.service.UserService;
import com.cscorner.helloapp.dto.UserRequestDTO;
import com.cscorner.helloapp.dto.UserResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE USER (POST)
    @PostMapping
    public UserResponseDTO createUser(
            @RequestBody UserRequestDTO dto) {
        return service.createUser(dto);
    }

    // GET USER BY ID (IMPORTANT ✅)
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }
}
