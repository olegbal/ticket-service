package com.github.olegbal.ticketservice.factories;

import com.github.olegbal.ticketservice.configurations.RegistrationProperties;
import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SerialNumberRolesFactory {

    private final RegistrationProperties registrationProperties;

    public Set<Role> getRolesList(String serialNumber) {

        Set<Role> rolesList = new HashSet<>();

        //TODO REMOVE HARDCODED CONDITION
        if (registrationProperties.getRegistrationCodes().get("organizer").equals(serialNumber)) {
            rolesList.add(createNewRole(Roles.ORGANIZER.roleId(), Roles.ORGANIZER.roleName()));
        } else {
            rolesList.add(createNewRole(Roles.USER.roleId(), Roles.USER.roleName()));
        }

        return rolesList;
    }

    private Role createNewRole(long roleId, String roleName) {

        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleId(roleId);
        return role;
    }

}
