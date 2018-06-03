package com.github.olegbal.ticketservice.services.user;

import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsersByRoles(Set<Role> rolesSet) {
        return userRepository.findAllByRoles(rolesSet);
    }

    @Override
    public User createUser(final User user) {
        user.setId(-1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        return getUserByLogin(login);
    }

    @Override
    public boolean isUserExists(String login, String email) {

        boolean possible = true;

        User checkLogin = getUserByLogin(login);

        if (checkLogin != null) {
            return true;
        }

        User checkEmail = getUserByEmail(email);

        if (checkEmail != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserExists(long id) {

        User user = getUserById(id);

        return user != null;
    }

    @Override
    public boolean removeUser(long id) {
        return false;
    }

    @Override
    public void removeAllUsers() {
        userRepository.deleteAll();

    }
}
