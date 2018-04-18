package com.github.olegbal.ticketservice.data.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAndPasswordDto {

    @NotEmpty
    @Size(min = 4, max = 15)
    private String login;

    @NotEmpty
    @Size(min = 6, max = 15)
    private String password;
}
