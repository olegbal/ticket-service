package com.github.olegbal.ticketservice.services.user;

import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultUserInfoService implements UserInfoService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserById(final long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User createUser(final User user) {
        user.setId(-1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        return getUserByLogin(login);
    }

    @Override
    public boolean isUserExists(String login) {

        User user = getUserByLogin(login);

        return user != null;
    }

    @Override
    public boolean isUserExists(long id) {

        User user = getUserById(id);

        return user != null;
    }
}
