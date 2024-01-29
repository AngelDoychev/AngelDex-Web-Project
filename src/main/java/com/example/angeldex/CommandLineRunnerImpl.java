package com.example.angeldex;

import com.example.angeldex.config.CMCAPIConfiguration;
import com.example.angeldex.model.entities.Role;
import com.example.angeldex.model.entities.UserEntity;
import com.example.angeldex.model.enums.RoleNameEnum;
import com.example.angeldex.service.RoleService;
import com.example.angeldex.service.UserEntityService;
import com.example.angeldex.util.JavaMailSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserEntityService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CMCAPIConfiguration cmcapiConfiguration;

    public CommandLineRunnerImpl(UserEntityService userService, RoleService roleService, PasswordEncoder passwordEncoder, CMCAPIConfiguration cmcapiConfiguration) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.cmcapiConfiguration = cmcapiConfiguration;
    }

    @Override
    public void run(String... args) throws Exception {
       // this.cmcapiConfiguration.addCurrencies(); //--adding currencies if there are none in the database
        // this.cmcapiConfiguration.updateCurrencies(); -- update currency prices
        if (this.userService.count() == 0) {
            initUserRoles();
            initUsers();
        }
    }

    private void initUsers() {
        UserEntity admin = new UserEntity();
        admin.setEmail("admin@email.com");
        admin.setPasswordHash(this.passwordEncoder.encode("secret"));
        admin.setRoles(List.of(this.roleService.findRoleByRoleName(RoleNameEnum.ADMIN),
                this.roleService.findRoleByRoleName(RoleNameEnum.USER)));
        this.userService.save(admin);

        UserEntity user = new UserEntity();
        user.setEmail("user@email.com");
        user.setPasswordHash(this.passwordEncoder.encode("secret"));
        user.setRoles(List.of(this.roleService.findRoleByRoleName(RoleNameEnum.USER)));
        this.userService.save(user);
    }


    private void initUserRoles() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleNameEnum.ADMIN);
        this.roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleNameEnum.USER);
        this.roleService.save(userRole);
    }
}
