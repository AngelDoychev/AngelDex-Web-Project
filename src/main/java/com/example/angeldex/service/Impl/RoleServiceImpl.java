package com.example.angeldex.service.Impl;

import com.example.angeldex.model.entities.Role;
import com.example.angeldex.model.enums.RoleNameEnum;
import com.example.angeldex.repository.RoleRepository;
import com.example.angeldex.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role findRoleByRoleName(RoleNameEnum roleName) {
        return this.roleRepository.findRoleByRoleName(roleName);
    }


}
