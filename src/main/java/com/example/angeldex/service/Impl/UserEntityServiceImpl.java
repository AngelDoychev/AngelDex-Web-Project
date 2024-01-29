package com.example.angeldex.service.Impl;

import com.example.angeldex.model.dtos.LoginServiceModel;
import com.example.angeldex.model.dtos.RegisterUserBindingDto;
import com.example.angeldex.model.entities.Role;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.repository.UserEntityRepository;
import com.example.angeldex.security.CurrentUser;
import com.example.angeldex.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserEntityServiceImpl(UserEntityRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public UserEntity mapUserFromUserDTO(LoginServiceModel userDTO) {
        return this.modelMapper.map(userDTO, UserEntity.class);
    }
    public UserEntity mapUserFromUserDTO(RegisterUserBindingDto userDTO) {
        return this.modelMapper.map(userDTO, UserEntity.class);
    }

    @Override
    public long count() {
        return this.userRepository.count();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity findByEmail(String email) {
        if (this.userRepository.findUserEntitiesByEmail(email).isPresent()) {
            return this.userRepository.findUserEntitiesByEmail(email).get();
        }
        return null;
    }

    @Override
    public Optional<UserEntity> findUserEntityById(long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public UserEntity updateUserEntity(UserEntity user) {
        return this.userRepository.save(user);
    }

    @Override
    public UserEntity createUserEntity(String email, String password, List<Role> roles) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPasswordHash(this.passwordEncoder.encode(password));
        user.setRoles(roles);
        return this.userRepository.save(user);
    }
    @Override
    public boolean authenticate(String email, String password) {
        if (this.userRepository.findUserEntitiesByEmail(email).isPresent()) {
            return this.passwordEncoder.matches(password, this.userRepository.findUserEntitiesByEmail(email).get().getPasswordHash());
        }
        return false;
    }

    @Override
    public void loginUser(String username) {
        this.currentUser.setAnonymous(false);
        this.currentUser.setUsername(username);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return this.userRepository.findAll();
    }


    @Override
    public void deleteUserEntityByID(long id) {
        this.userRepository.deleteById(id);
    }

}
