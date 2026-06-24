package com.cscorner.helloapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.cscorner.helloapp.model.Passport;
import com.cscorner.helloapp.repository.PassportRepository;


@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Passport savePassport(Passport passport) {
        if (passport == null) {
            throw new IllegalArgumentException("Passport cannot be null");
        }
        return passportRepository.save(passport);
    }

    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }
}

