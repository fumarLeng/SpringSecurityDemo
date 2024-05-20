package com.example.springsecuritydemo.handler;

import com.example.springsecuritydemo.util.RedisCache;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 認證成功處理
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedisCache redisCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    
    }
}
