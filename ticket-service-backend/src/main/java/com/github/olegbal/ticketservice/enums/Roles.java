package com.github.olegbal.ticketservice.enums;

public enum Roles {
    ADMIN("ROLE_ADMIN", 1),
    ORGANIZER("ROLE_ORGANIZER", 2),
    USER("ROLE_USER", 3);

    private String roleName;
    private long roleId;

    Roles(String roleName, long roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public String roleName() {
        return roleName;
    }

    public long roleId() {
        return roleId;
    }
}
