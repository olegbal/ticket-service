package com.github.olegbal.ticketservice.controllers.user;

import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.services.auth.LoginService;
import com.github.olegbal.ticketservice.services.auth.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    private RegistrationService registrationService;
    private LoginService loginService;

    @Before
    public void setUp() {
        loginService = Mockito.mock(LoginService.class);
        registrationService = Mockito.mock(RegistrationService.class);

        when(registrationService.registerUser(anyObject())).thenReturn(new UserDto());
        when(registrationService.registerUser(null)).thenReturn(null);
        when(loginService.logIn(anyObject(), anyObject())).thenReturn(new UserDto());
        when(loginService.logIn(null, null)).thenReturn(null);
    }

    @Test
    public void signUp() {
        //TODO
    }

    @Test
    public void signIn() {
        //TODO
    }
}