package com.example.angeldex.repository;

import com.example.angeldex.model.entities.RegisteredEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredEmailRepository extends JpaRepository<RegisteredEmail, Long> {
    RegisteredEmail findByEmail(String email);
}
