package com.github.olegbal.ticketservice.handlers;

import com.github.olegbal.ticketservice.entities.User;

public interface TokenHandler {

    User parseUserFromToken(String token);

    String createTokenForUser(User user);
}
