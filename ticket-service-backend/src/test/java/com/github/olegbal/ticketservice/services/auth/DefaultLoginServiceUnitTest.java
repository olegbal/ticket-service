package com.github.olegbal.ticketservice.services.auth;

import com.github.olegbal.ticketservice.testutils.UserCreator;
import com.github.olegbal.ticketservice.data.auth.LoginAndPasswordDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.security.TokenAuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class DefaultLoginServiceUnitTest {

    private TokenAuthenticationService tokenAuthenticationService;
    private ConversionService conversionService;
    private LoginService loginService;

    @Before
    public void setUp() {
        tokenAuthenticationService = Mockito.mock(TokenAuthenticationService.class);
        conversionService = Mockito.mock(ConversionService.class);
        loginService = new DefaultLoginService(tokenAuthenticationService, conversionService);

        when(conversionService.convert(anyObject(), eq(UserDto.class)))
                .thenReturn(UserCreator.createUserDtoWithRoleUser(1L, "login1"));
        when(tokenAuthenticationService.loadLoginData(anyObject(), anyObject()))
                .thenReturn(UserCreator.createUserWithRoleUser(1L, "login1"));
        when(tokenAuthenticationService.loadLoginData(null, null))
                .thenReturn(null);

    }

    @Test
    public void logIn() {

        UserDto userDto = loginService.logIn(new LoginAndPasswordDto(), null);

        assertThat(userDto).isNotNull();

        userDto = loginService.logIn(null, null);

        assertThat(userDto).isNull();

    }
}