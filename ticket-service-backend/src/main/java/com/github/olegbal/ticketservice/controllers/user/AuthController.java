package com.github.olegbal.ticketservice.controllers.user;

import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(V1)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {

    private UserInfoService userInfoService;

    @PostMapping
    @RequestMapping("register")
    public ResponseEntity signUp(@RequestBody User user) {
        userInfoService.createUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
