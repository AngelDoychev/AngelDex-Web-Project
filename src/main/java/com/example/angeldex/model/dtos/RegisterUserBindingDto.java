package com.example.angeldex.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterUserBindingDto {

    @Email(message = "Please enter a valid email address.")
    @NotEmpty(message = "Cannot register an empty email.")
    public String email;
    @Size(min = 3, message = "Password must be more than 3 characters long.")
    @NotEmpty
    public String passwordHash;

    public RegisterUserBindingDto() {
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
