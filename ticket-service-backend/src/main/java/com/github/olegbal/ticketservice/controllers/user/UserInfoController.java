package com.github.olegbal.ticketservice.controllers.user;

import com.github.olegbal.ticketservice.data.ChangePasswordDto;
import com.github.olegbal.ticketservice.data.auth.UserDto;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final ConversionService conversionService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(path = "/users/{id}")
    public ResponseEntity getUserById(@PathVariable long id) {
        User user = userInfoService.getUserById(id);
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/users", params = "login")
    public ResponseEntity getUserByLogin(@PathParam(value = "login") String login) {
        User user = userInfoService.getUserByLogin(login);
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(path = "/users", params = "roleId")
    public ResponseEntity getUsersByRolesList(@RequestParam(value = "roleId") long[] rolesIds) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(path = "/users", params = "eventId")
    public ResponseEntity getUserByEventId(@PathParam(value = "eventId") long eventId) {
        User user = userInfoService.getUserByEventId(eventId);
        UserDto userDto = conversionService.convert(user, UserDto.class);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "/users")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User user = userInfoService.getUserById(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setOrganization(userDto.getOrganization());

        user = userInfoService.updateUser(user);

        UserDto updatedDto = conversionService.convert(user, UserDto.class);

        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @PutMapping(path = "/users/change-password")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto changePasswordDto) {

        User user = userInfoService.getUserById(changePasswordDto.getId());

        if (!bCryptPasswordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }

        String newCryptedPassword = bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword());
        user.setPassword(newCryptedPassword);
        user = userInfoService.updateUser(user);

        UserDto convertedUser = conversionService.convert(user, UserDto.class);

        return new ResponseEntity<>(convertedUser, HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
