package com.github.olegbal.ticketservice.testutils;

import com.github.olegbal.ticketservice.converters.UserEntityToDtoConverter;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.enums.Roles;

import java.util.Collections;

public class UserCreator {

    private static final UserEntityToDtoConverter userEntityToDtoConverter = new UserEntityToDtoConverter();

    public static User createUserWithRoleUser(long id, String login) {
        User user = createUser(id, login);
        Role role = new Role(Roles.USER.roleId(), Roles.USER.roleName());
        user.setRoles(Collections.singleton(role));
        return user;
    }

    public static UserDto createUserDtoWithRoleUser(long id, String login) {
        User user = createUserWithRoleUser(id, login);
        return userEntityToDtoConverter.convert(user);
    }

    public static User createUserWithRoleOrganizer(long id, String login) {
        User user = createUser(id, login);
        Role role = new Role(Roles.ORGANIZER.roleId(), Roles.ORGANIZER.roleName());
        user.setRoles(Collections.singleton(role));
        return user;
    }

    public static UserDto createUserDtoWithRoleOrganizer(long id, String login) {
        User user = createUserWithRoleOrganizer(id, login);
        return userEntityToDtoConverter.convert(user);
    }

    private static User createUser(long id, String login) {
        return new User(id, login, "testPass", "testName", "testLastName",
                "testOrg", "testNumber", "testMail", null, null, null);
    }

    public static UserDto convertToUserDto(User user) {
        return userEntityToDtoConverter.convert(user);
    }

}
