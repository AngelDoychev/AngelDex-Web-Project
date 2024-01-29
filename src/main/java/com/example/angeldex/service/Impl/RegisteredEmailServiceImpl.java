package com.example.angeldex.service.Impl;

import com.example.angeldex.model.entities.RegisteredEmail;
import com.example.angeldex.repository.RegisteredEmailRepository;
import com.example.angeldex.service.RegisteredEmailService;
import org.springframework.stereotype.Service;

@Service
public class RegisteredEmailServiceImpl implements RegisteredEmailService {
    private final RegisteredEmailRepository registeredEmailRepository;

    public RegisteredEmailServiceImpl(RegisteredEmailRepository registeredEmailRepository) {
        this.registeredEmailRepository = registeredEmailRepository;
    }

    @Override
    public RegisteredEmail save(RegisteredEmail registeredEmail) {
        return this.registeredEmailRepository.save(registeredEmail);
    }

    @Override
    public RegisteredEmail findByEmail(String email) {
        return this.registeredEmailRepository.findByEmail(email);
    }
}
