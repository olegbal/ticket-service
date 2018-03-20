package com.github.olegbal.ticketservice.data.auth;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class LoginAndPasswordDto {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
