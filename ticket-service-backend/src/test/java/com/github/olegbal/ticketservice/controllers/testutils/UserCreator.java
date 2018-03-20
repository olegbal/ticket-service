package com.github.olegbal.ticketservice.controllers.testutils;

import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.enums.Roles;

import java.util.Collections;

public class UserCreator {

    public static User createUserWithRoleUser(long id, String login) {
        User user = createUser(id, login);
        Role role = new Role(Roles.USER.roleId(), Roles.USER.roleName());
        user.setRoles(Collections.singleton(role));
        return user;
    }

    public static User createUserWithRoleOrganizer(long id, String login) {
        User user = createUser(id, login);
        Role role = new Role(Roles.ORGANIZER.roleId(), Roles.ORGANIZER.roleName());
        user.setRoles(Collections.singleton(role));
        return user;
    }

    private static User createUser(long id, String login) {
        return new User(id, login, "testPass", "testName", "testLastName",
                "testOrg", "testNumber", "testMail", null);
    }

}
