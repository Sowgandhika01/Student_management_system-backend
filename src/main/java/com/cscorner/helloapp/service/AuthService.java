package com.cscorner.helloapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cscorner.helloapp.dto.AuthResponse;
import com.cscorner.helloapp.dto.LoginRequest;
import com.cscorner.helloapp.dto.SignupRequest;
import com.cscorner.helloapp.dto.SignupResponse;
import com.cscorner.helloapp.model.AppUser;
import com.cscorner.helloapp.repository.AuthUserRepository;
import com.cscorner.helloapp.security.JwtUtil;

@Service
public class AuthService {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final String adminCode;

    public AuthService(AuthUserRepository repository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       @Value("${security.admin.code}") String adminCode) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.adminCode = adminCode;
    }

    public SignupResponse signup(SignupRequest request) {
        if (request.email == null || request.email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (request.password == null || request.password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (repository.existsByEmail(request.email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        String role = "ROLE_USER";
        if (request.role != null && !request.role.isBlank()) {
            role = request.role.trim().toUpperCase();
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }
        }

        if ("ROLE_ADMIN".equals(role)) {
            if (request.adminCode == null || !request.adminCode.equals(adminCode)) {
                throw new IllegalArgumentException("Admin signup code is invalid");
            }
        }

        AppUser user = new AppUser();
        user.setName(request.name);
        user.setEmail(request.email.trim());
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(role);

        AppUser saved = repository.save(user);
        return new SignupResponse(saved.getId(), saved.getEmail(), saved.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        AppUser user = repository.findByEmail(request.email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}
