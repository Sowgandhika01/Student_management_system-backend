package com.cscorner.helloapp.repository;

import com.cscorner.helloapp.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}