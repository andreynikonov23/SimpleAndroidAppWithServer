package ru.nngasu.security;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nngasu.dao.implementation.UserService;
import ru.nngasu.models.User;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    private final HashMap<String, String> refreshStorage = new HashMap<>();

    public JwtResponse login(JwtRequest jwtRequest) throws AuthException {
        User user = userService.findByUsername(jwtRequest.getUsername());
        if (user != null && user.getPassword().equals(jwtRequest.getPassword())) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Invalid username or password");
        }
    }

    public JwtResponse getAccessToken(String refreshToken) throws AuthException {
        if(jwtService.validateRefreshToken(refreshToken)) {
            Claims claims = jwtService.getRefreshClaims(refreshToken);
            String username = claims.getSubject();
            String savedRefreshToken = refreshStorage.get(username);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                User user = userService.findByUsername(username);
                if(user == null) { throw new AuthException("User is not found");}
                String accessToken = jwtService.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(String refreshToken) throws AuthException {
        String accessToken = "";
        String newRefreshToken = "";
        if (jwtService.validateRefreshToken(refreshToken)) {
            Claims claims = jwtService.getRefreshClaims(refreshToken);
            String username = claims.getSubject();
            String savedRefreshToken = refreshStorage.get(username);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                User user = userService.findByUsername(username);
                if(user == null) {throw new AuthException("User is not found");}
                accessToken = jwtService.generateAccessToken(user);
                newRefreshToken = jwtService.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
            }
        } else
            throw new AuthException("Invalid JWT token");
        return new JwtResponse(accessToken, newRefreshToken);
    }

    public JwtAuthentication getInfoAuth() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
