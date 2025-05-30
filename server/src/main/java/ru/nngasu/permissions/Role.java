package ru.nngasu.permissions;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    MANAGER(Set.of(Permission.ORDER, Permission.PROCESS_ORDER)),
    CLIENT(Set.of(Permission.ORDER));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermissionString())).collect(Collectors.toSet());
    }
}
