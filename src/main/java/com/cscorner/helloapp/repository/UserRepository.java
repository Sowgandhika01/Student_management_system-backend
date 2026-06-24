package com.cscorner.helloapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cscorner.helloapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
