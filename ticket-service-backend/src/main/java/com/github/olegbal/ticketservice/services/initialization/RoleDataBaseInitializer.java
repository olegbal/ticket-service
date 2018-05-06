package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.enums.Roles;
import com.github.olegbal.ticketservice.services.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Order(1)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleDataBaseInitializer implements DataBaseInitializer {

    private final RoleService roleService;

    @PostConstruct
    @Override
    public void initializeData() {
        Role user = new Role(Roles.USER.roleId(), Roles.USER.roleName());
        Role organizer = new Role(Roles.ORGANIZER.roleId(), Roles.ORGANIZER.roleName());
        Role admin = new Role(Roles.ADMIN.roleId(), Roles.ADMIN.roleName());

        roleService.createRole(user);
        roleService.createRole(organizer);
        roleService.createRole(admin);
    }
}
