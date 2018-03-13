package com.github.olegbal.ticketservice.services.user;

import com.github.olegbal.ticketservice.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoService extends UserDetailsService {
    User getUserById(long id);

    User getUserByLogin(String login);

    User createUser(User user);

    User loadUserByUsername(String login);

}
