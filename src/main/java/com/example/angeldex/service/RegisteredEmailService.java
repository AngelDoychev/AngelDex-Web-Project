package com.example.angeldex.service;

import com.example.angeldex.model.entities.RegisteredEmail;

public interface RegisteredEmailService {
    RegisteredEmail save(RegisteredEmail registeredEmail);

    RegisteredEmail findByEmail(String email);
}
