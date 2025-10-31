package com.ua.FindProjects.security.filters;

import com.ua.FindProjects.security.JwtTool;
import java.io.IOException;

import com.ua.FindProjects.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

@RequiredArgsConstructor
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTool jwtTool;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = jwtTool.getTokenFromHttpServletRequest(request);
        if (token != null && jwtTool.isTokenValid(token, jwtTool.getAccessTokenKey())) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(token, null));
            Optional.ofNullable(authentication.getPrincipal())
                    .ifPresent(p -> SecurityContextHolder.getContext().setAuthentication(authentication));
        }
        chain.doFilter(request, response);
    }
}
