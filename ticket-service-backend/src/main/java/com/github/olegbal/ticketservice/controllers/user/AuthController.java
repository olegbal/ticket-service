package com.github.olegbal.ticketservice.controllers.user;

import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.security.TokenAuthenticationService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(V1)
public class AuthController {

    private UserInfoService userInfoService;
    private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public AuthController(final UserInfoService userInfoService, TokenAuthenticationService tokenAuthenticationService) {
        this.userInfoService = userInfoService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity signUp(@RequestBody final User user) {
        userInfoService.createUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity signIn(HttpServletResponse httpServletResponse, @RequestBody final User user) {

        if (tokenAuthenticationService.checkLogin(httpServletResponse, user)) {
            return new ResponseEntity<>(userInfoService.getUserByLogin(user.getLogin()).getRoles(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
