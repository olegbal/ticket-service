package com.github.olegbal.ticketservice.services.user;

import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.github.olegbal.ticketservice.testutils.UserCreator.createUserWithRoleOrganizer;
import static com.github.olegbal.ticketservice.testutils.UserCreator.createUserWithRoleUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class DefaultUserInfoServiceUnitTest {

    private UserRepository userRepository;
    private UserInfoService userInfoService;

    @Before
    public void setUp() {

        userRepository = Mockito.mock(UserRepository.class);
        userInfoService = new DefaultUserInfoService(userRepository, new BCryptPasswordEncoder());

        when(userRepository.findOne(1L)).thenReturn(createUserWithRoleOrganizer(1L, "login1"));

        when(userRepository.findByLogin("login1")).thenReturn(createUserWithRoleOrganizer(1L, "login1"));

        when(userRepository.save((User) anyObject())).thenReturn(createUserWithRoleOrganizer(1L, "login1"));
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
        User user = createUserWithRoleUser(-1L, "login1");
        user = userInfoService.createUser(user);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotEqualTo(-1L);
    }

    @Test
    public void isUserExistsById() {
        boolean result = userInfoService.isUserExists(1L);

        assertThat(result).isTrue();

        result = userInfoService.isUserExists(-1L);
        assertThat(result).isFalse();

    }

    @Test
    public void isUserExistsByLogin() {
        boolean result = userInfoService.isUserExists("login1");

        assertThat(result).isTrue();

        result = userInfoService.isUserExists("noSuchLogin");
        assertThat(result).isFalse();
    }
}