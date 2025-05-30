package ru.nngasu.controllers;

import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nngasu.dao.DAO;
import ru.nngasu.models.User;
import ru.nngasu.security.AuthService;
import ru.nngasu.security.JwtRequest;
import ru.nngasu.security.JwtResponse;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    private DAO<User, Integer> userDAO;

    @Autowired
    public AuthController(DAO<User, Integer> userDAOImpl) {
        this.userDAO = userDAOImpl;
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = null;
        try {
            jwtResponse = authService.login(jwtRequest);
        } catch (AuthException e) {
            return ResponseEntity.status(401).body("Unauthorized access");
        }
        return ResponseEntity.ok(jwtResponse);
    }
}
