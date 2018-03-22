package com.github.olegbal.ticketservice.matchers;

import com.github.olegbal.ticketservice.data.auth.LoginAndPasswordDto;
import org.mockito.ArgumentMatcher;

public class LoginDtoMatcher extends ArgumentMatcher<LoginAndPasswordDto> {

    private String login;

    public LoginDtoMatcher(String login) {
        this.login = login;
    }

    @Override
    public boolean matches(Object argument) {
        return ((LoginAndPasswordDto) argument).getLogin().equals(login);
    }
}
