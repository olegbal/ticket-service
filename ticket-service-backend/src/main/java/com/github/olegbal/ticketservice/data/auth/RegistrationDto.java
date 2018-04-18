package com.github.olegbal.ticketservice.data.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    @NotEmpty
    @Size(min = 4, max = 15)
    private String login;

    @NotEmpty
    @Size(min = 6, max = 15)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 254)
    private String email;

    private String firstName;
    private String lastName;
    private String organization;
    private String phoneNumber;
    private String secretCode;
}
