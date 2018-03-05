package com.github.olegbal.ticketservice.enums;

public enum Roles {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    ORGANIZER("ROLE_ORGANIZER");

    private String roleName;


    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String roleName() {
        return roleName;
    }
}
