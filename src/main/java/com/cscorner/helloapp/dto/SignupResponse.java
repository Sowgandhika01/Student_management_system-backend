package com.cscorner.helloapp.dto;

public class SignupResponse {
    public Long id;
    public String email;
    public String role;

    public SignupResponse(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
