package ru.nngasu.permissions;

public enum Permission {
    ORDER("order"),
    PROCESS_ORDER("processOrder");

    private final String permissionString;

    Permission(String permissionString) {
        this.permissionString = permissionString;
    }

    public String getPermissionString() {
        return permissionString;
    }
}
