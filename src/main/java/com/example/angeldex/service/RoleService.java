package com.example.angeldex.service;

import com.example.angeldex.model.entities.Role;
import com.example.angeldex.model.enums.RoleNameEnum;

public interface RoleService {
    Role save(Role role);
    Role findRoleByRoleName(RoleNameEnum roleName);
}
