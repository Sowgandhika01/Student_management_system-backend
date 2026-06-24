package com.cscorner.helloapp.dto;

public class UserResponseDTO {

    public Long id;
    public String name;
    public String email;

    public UserResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
