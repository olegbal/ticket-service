package com.github.olegbal.ticketservice.services.auth;

import com.github.olegbal.ticketservice.data.auth.LoginAndPasswordDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.security.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultLoginService implements LoginService {

    private final TokenAuthenticationService authenticationService;
    private final ConversionService conversionService;

    @Override
    public UserDto logIn(LoginAndPasswordDto loginAndPasswordDto, HttpServletResponse response) {

        User user = authenticationService.loadLoginData(response, loginAndPasswordDto);

        if (user == null) {
            return null;
        }
        return conversionService.convert(user, UserDto.class);
    }
}
