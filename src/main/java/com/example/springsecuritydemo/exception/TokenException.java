package com.example.springsecuritydemo.exception;

import javax.security.sasl.AuthenticationException;

public class TokenException extends AuthenticationException {
    public TokenException(String msg) {
        super(msg);
    }
}
