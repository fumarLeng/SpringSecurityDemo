package com.example.springsecuritydemo.exception;


import org.springframework.security.core.AuthenticationException;

public class CustomerAuthException extends AuthenticationException {
    public CustomerAuthException(String msg) {
        super(msg);
    }
}
