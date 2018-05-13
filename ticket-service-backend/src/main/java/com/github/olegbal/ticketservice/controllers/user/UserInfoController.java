package com.github.olegbal.ticketservice.controllers.user;

import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final ConversionService conversionService;

    @GetMapping(path = "/users/{id}")
    public ResponseEntity getUserById(@PathVariable long id) {
        User user = userInfoService.getUserById(id);
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{login}")
    public ResponseEntity getUserByLogin(@PathVariable String login) {
        User user = userInfoService.getUserByLogin(login);
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/users", params = "roleId")
    public ResponseEntity getUsersByRolesList(@RequestParam(value = "roleId") List<Long> rolesIds) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(path = "/users")
    public ResponseEntity createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "/users")
    public ResponseEntity updateUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
