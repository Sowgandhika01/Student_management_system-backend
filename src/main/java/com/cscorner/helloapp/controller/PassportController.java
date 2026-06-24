package com.cscorner.helloapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.cscorner.helloapp.model.Passport;
import com.cscorner.helloapp.service.PassportService;

@RestController
@RequestMapping("/passports")

public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping
    public Passport createPassport(@RequestBody Passport passport) {
        return passportService.savePassport(passport);
    }

    @GetMapping
    public List<Passport> getAllPassports() {
        return passportService.getAllPassports();
    }

}
