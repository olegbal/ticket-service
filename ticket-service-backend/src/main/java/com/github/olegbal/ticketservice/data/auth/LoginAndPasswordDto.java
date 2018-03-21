package com.github.olegbal.ticketservice.data.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAndPasswordDto {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
