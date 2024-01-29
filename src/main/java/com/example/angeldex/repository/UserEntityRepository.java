package com.example.angeldex.repository;

import com.example.angeldex.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntitiesByEmail(String email);

    Optional<Object> findUserEntitiesByEmailAndPasswordHash(String username, String password);

}
