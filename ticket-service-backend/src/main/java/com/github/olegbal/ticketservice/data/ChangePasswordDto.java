package com.github.olegbal.ticketservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
    private long id;
    private String currentPassword;
    private String newPassword;
}
