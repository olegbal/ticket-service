package com.github.olegbal.ticketservice.data.auth;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class RegistrationDto {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    private String firstName;
    private String lastName;
    private String organization;
    private String phoneNumber;
    private String secretCode;
}
