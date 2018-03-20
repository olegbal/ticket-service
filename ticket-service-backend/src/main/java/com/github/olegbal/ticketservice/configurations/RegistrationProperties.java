package com.github.olegbal.ticketservice.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties
public class RegistrationProperties {

    private Map<String, String> registrationCodes;
}
