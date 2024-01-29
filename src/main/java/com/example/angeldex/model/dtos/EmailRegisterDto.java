package com.example.angeldex.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class EmailRegisterDto {
    @Email(message = "Not a valid email.")
    @NotEmpty(message = "Cannot register an empty email.")
    public String email;

    public EmailRegisterDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
