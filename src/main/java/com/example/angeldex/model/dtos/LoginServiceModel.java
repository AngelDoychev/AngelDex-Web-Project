package com.example.angeldex.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginServiceModel {
    @Email
    @NotEmpty(message = "Cannot login with an empty email.")
    public String email;
    @NotEmpty(message = "Cannot login with an empty password.")
    public String passwordHash;

    public LoginServiceModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
