package com.github.olegbal.ticketservice.data.auth;

import com.github.olegbal.ticketservice.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String login;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String orgnaization;
    private String email;
    private Set<Role> roles;
}
