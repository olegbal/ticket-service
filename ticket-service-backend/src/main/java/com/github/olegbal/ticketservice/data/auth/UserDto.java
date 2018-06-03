package com.github.olegbal.ticketservice.data.auth;

import com.github.olegbal.ticketservice.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String organization;
    private String email;
    private Set<Role> roles;
}
