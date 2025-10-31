package com.ua.FindProjects.security.security_service_api;

import jakarta.servlet.http.HttpServletResponse;

public interface TokenService {
    public void passTokenToCookies(String accessToken, HttpServletResponse response);
}
