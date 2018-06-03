package com.github.olegbal.ticketservice.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.olegbal.ticketservice.data.auth.LoginAndPasswordDto;
import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.matchers.LoginDtoMatcher;
import com.github.olegbal.ticketservice.matchers.RegistrationDtoLoginMatcher;
import com.github.olegbal.ticketservice.security.SecurityConfig;
import com.github.olegbal.ticketservice.security.TokenAuthenticationService;
import com.github.olegbal.ticketservice.services.auth.LoginService;
import com.github.olegbal.ticketservice.services.auth.RegistrationService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import com.github.olegbal.ticketservice.testutils.RegistrationDtoCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;
    @MockBean
    private LoginService loginService;
    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;
    @MockBean
    private UserInfoService userInfoService;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() {
        when(registrationService.registerUser(anyObject(), anyObject())).thenReturn(new UserDto());
        when(registrationService.registerUser(argThat(new RegistrationDtoLoginMatcher("failedUser")), anyObject())).thenReturn(null);

        when(loginService.logIn(anyObject(), anyObject())).thenReturn(new UserDto());
        when(loginService.logIn(argThat(new LoginDtoMatcher("failedUser")), anyObject())).thenReturn(null);
    }

    @Test
    public void signUp() throws Exception {

        RegistrationDto validUser = RegistrationDtoCreator.createUserData("user1", "user1@user.com", "123456");
        RegistrationDto userWithoutLogin = RegistrationDtoCreator.createUserData(null, "user1@user.com", "123456");
        RegistrationDto userWithoutEmail = RegistrationDtoCreator.createUserData("user1", null, "123456");
        RegistrationDto userWithoutPassword = RegistrationDtoCreator.createUserData("user2", "user2@user.com", "123456");
        RegistrationDto failedUser = RegistrationDtoCreator.createUserData("failedUser", "user2@user.com", "123456");

        userWithoutPassword.setPassword(null);

        ObjectMapper om = new ObjectMapper();

        this.mockMvc.perform(post(V1 + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(validUser)))
                .andExpect(status().isOk());

        this.mockMvc.perform(post(V1 + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(userWithoutLogin)))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post(V1 + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(userWithoutEmail)))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post(V1 + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(userWithoutPassword)))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post(V1 + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(failedUser)))
                .andExpect(status().isNotAcceptable());
        //TODO
    }

    @Test
    public void signIn() throws Exception {
        LoginAndPasswordDto loginAndPasswordDto = new LoginAndPasswordDto("user1", "1234");
        LoginAndPasswordDto formWithoutLogin = new LoginAndPasswordDto(null, "1234");
        LoginAndPasswordDto formWithoutPassword = new LoginAndPasswordDto("user1", null);
        LoginAndPasswordDto failedUser = new LoginAndPasswordDto("failedUser", "failedUser");

        this.mockMvc.perform(post(V1 + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(loginAndPasswordDto)))
                .andExpect(status().isAccepted());

        this.mockMvc.perform(post(V1 + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(formWithoutLogin)))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post(V1 + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(formWithoutPassword)))
                .andExpect(status().isBadRequest());

        this.mockMvc.perform(post(V1 + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(failedUser)))
                .andExpect(status().isUnauthorized());


        //TODO
    }
}