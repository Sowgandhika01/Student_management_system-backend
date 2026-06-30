package com.cscorner.helloapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cscorner.helloapp.model.AppUser;

public interface AuthUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
