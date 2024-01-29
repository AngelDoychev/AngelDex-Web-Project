package com.example.angeldex.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "registered_emails")
public class RegisteredEmail extends BaseEntity {
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public RegisteredEmail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
