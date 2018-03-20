package com.github.olegbal.ticketservice.services.user;

import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.github.olegbal.ticketservice.controllers.testutils.UserCreator.createUserWithRoleOrganizer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DefaultUserInfoServiceUnitTest {

    private UserRepository userRepository;
    private UserInfoService userInfoService;

    @Before
    public void setUp() {

        userRepository = Mockito.mock(UserRepository.class);
        userInfoService = new DefaultUserInfoService(userRepository, new BCryptPasswordEncoder());

        when(userRepository.findOne(1L)).thenReturn(createUserWithRoleOrganizer(1L, "login1"));
        when(userRepository.findOne(2L)).thenReturn(createUserWithRoleOrganizer(2L, "login2"));

        when(userRepository.findByLogin("login1")).thenReturn(createUserWithRoleOrganizer(1L, "login1"));
        when(userRepository.findByLogin("login2")).thenReturn(createUserWithRoleOrganizer(2L, "login2"));
    }


    @Test
    public void getUserById() {
        User user = userInfoService.getUserById(1L);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);

    }

    @Test
    public void getUserByLogin() {
        User user = userInfoService.getUserByLogin("login1");

        assertThat(user).isNotNull();
        assertThat(user.getLogin()).isEqualTo("login1");
    }

    @Test
    public void createUser() {
        
    }

    @Test
    public void loadUserByUsername() {
    }

    @Test
    public void isUserExists() {
    }

    @Test
    public void isUserExists1() {
    }
}