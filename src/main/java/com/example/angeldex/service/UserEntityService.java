package com.example.angeldex.service;

import com.example.angeldex.model.entities.Role;
import com.example.angeldex.model.entities.UserEntity;


import java.util.List;
import java.util.Optional;

public interface UserEntityService  {
    long count();
    UserEntity save(UserEntity user);
    List<UserEntity> findAll();
    UserEntity findByEmail(String email);
    Optional<UserEntity> findUserEntityById(long id);
    UserEntity updateUserEntity(UserEntity user);
    UserEntity createUserEntity(String email, String password, List<Role> roles);
    void deleteUserEntityByID(long id);
    boolean authenticate(String username, String password);

    void loginUser(String email);

    List<UserEntity> findAllUsers();
}
