package com.github.olegbal.ticketservice.services.user;

import com.github.olegbal.ticketservice.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoService extends UserDetailsService {
    public User getUserById(long id);

    public User getUserByLogin(String login);

    public User createUser(User user);
}
