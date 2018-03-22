package com.github.olegbal.ticketservice.matchers;


import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import org.mockito.ArgumentMatcher;

public class RegistrationDtoLoginMatcher extends ArgumentMatcher<RegistrationDto> {

    private String login;

    public RegistrationDtoLoginMatcher(String login) {
        this.login = login;
    }

    @Override
    public boolean matches(Object argument) {

        return ((RegistrationDto) argument).getLogin().equals(login);
    }
}
