package com.github.olegbal.ticketservice.services.user;

import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserInfoService extends UserDetailsService {
    User getUserById(long id);

    User getUserByLogin(String login);

    User getUserByEmail(String email);

    User getUserByEventId(long eventId);

    List<User> getUsersByRoles(Set<Role> rolesSet);

    User createUser(User user);

    User updateUser(User user);

    User loadUserByUsername(String login);

    boolean isUserExists(String login, String email);

    boolean isUserExists(long id);

    boolean removeUser(long id);

    void removeAllUsers();

}
