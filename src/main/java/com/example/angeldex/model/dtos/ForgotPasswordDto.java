package com.example.angeldex.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ForgotPasswordDto {
    @Email(message = "Email is invalid.")
    @NotEmpty(message = "Cannot look for an empty email.")
    public String email;

    public ForgotPasswordDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
