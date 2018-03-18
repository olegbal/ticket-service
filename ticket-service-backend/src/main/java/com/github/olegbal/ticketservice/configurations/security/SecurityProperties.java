package com.github.olegbal.ticketservice.configurations.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "token")
public class SecurityProperties {

    /**
     * Name of cookie that contain auth token value.
     */
    private String cookieName;

    /**
     * Secret of auth token for encrypting.
     */
    private String secret;

}


