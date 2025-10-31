package com.ua.FindProjects.controllers;

import com.ua.FindProjects.DTOs.auth.LoginRequest;
import com.ua.FindProjects.entitys.User;
import com.ua.FindProjects.security.JwtTool;
import com.ua.FindProjects.security.security_service_api.TokenService;
import com.ua.FindProjects.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTool jwtTool;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        User user = userService.findNotDeactivatedByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getOwnSecurity().getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTool.createAccessToken(user.getEmail(), user.getRole());
        tokenService.passTokenToCookies(token, response);

        return ResponseEntity.ok().build();
    }
}

