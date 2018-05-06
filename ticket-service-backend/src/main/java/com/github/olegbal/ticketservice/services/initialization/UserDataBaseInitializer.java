package com.github.olegbal.ticketservice.services.initialization;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataBaseInitializer {
}
