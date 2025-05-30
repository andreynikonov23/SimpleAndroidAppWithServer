package ru.nngasu.security;

import ch.qos.logback.core.util.StringUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {
    private final JwtService jwtService;
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String accessToken = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (accessToken != null && jwtService.validateAccessToken(accessToken)) {
            Claims claims = jwtService.getAccessClaims(accessToken);
            JwtAuthentication jwtAuthentication = JwtUtils.generate(claims);
            jwtAuthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public String getTokenFromRequest(HttpServletRequest servletRequest) {
        String bearer = servletRequest.getHeader(AUTHORIZATION);
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
