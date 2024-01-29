package com.example.angeldex.repository;

import com.example.angeldex.model.entities.Role;
import com.example.angeldex.model.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRoleName(RoleNameEnum roleName);
}
