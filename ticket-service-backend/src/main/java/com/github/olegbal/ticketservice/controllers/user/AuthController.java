package com.github.olegbal.ticketservice.controllers.user;

import com.github.olegbal.ticketservice.data.auth.LoginAndPasswordDto;
import com.github.olegbal.ticketservice.data.auth.RegistrationDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.security.TokenAuthenticationService;
import com.github.olegbal.ticketservice.security.UserAuthentication;
import com.github.olegbal.ticketservice.services.auth.LoginService;
import com.github.olegbal.ticketservice.services.auth.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final RegistrationService registrationService;
    private final TokenAuthenticationService authenticationService;
    private final LoginService loginService;
    private final ConversionService conversionService;

    @GetMapping(path = "/check-auth")
    public ResponseEntity checkToken(HttpServletRequest request) {
        UserAuthentication user = (UserAuthentication) authenticationService.getAuthentication(request);

        UserDto userDto = null;

        if (user.getDetails() != null) {
            userDto = conversionService.convert(user.getDetails(), UserDto.class);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity signUp(@RequestBody @Valid final RegistrationDto registrationDto) {

        UserDto registeredUser = registrationService.registerUser(registrationDto);

        if (registeredUser == null) {
            //TODO THROW EXCEPTION
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity signIn(HttpServletResponse response, @RequestBody @Valid final LoginAndPasswordDto loginForm) {

        UserDto user = loginService.logIn(loginForm, response);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
