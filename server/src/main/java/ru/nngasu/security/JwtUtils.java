package ru.nngasu.security;

import io.jsonwebtoken.Claims;
import ru.nngasu.permissions.Permission;
import ru.nngasu.permissions.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUtils {
    private JwtUtils() {}

    public static JwtAuthentication generate(Claims claims) {
        JwtAuthentication jwtAuthentication = new JwtAuthentication();
        jwtAuthentication.setRole(claims.get("role", Role.class));
        jwtAuthentication.setName(claims.get("name", String.class));
        jwtAuthentication.setUsername(claims.getSubject());
        return jwtAuthentication;
    }
}
