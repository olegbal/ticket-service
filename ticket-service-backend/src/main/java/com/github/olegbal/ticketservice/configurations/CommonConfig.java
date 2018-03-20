package com.github.olegbal.ticketservice.configurations;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({RegistrationProperties.class})
public class CommonConfig {
}
