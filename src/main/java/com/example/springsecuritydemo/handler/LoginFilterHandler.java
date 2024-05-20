package com.example.springsecuritydemo.handler;

import com.alibaba.fastjson2.JSONObject;
import com.example.springsecuritydemo.exception.CustomerAuthException;
import com.example.springsecuritydemo.result.ResultObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFilterHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        String str = null;
        int code = 500;

        if(exception instanceof AccountExpiredException){
            str = "帳號過期, 登入失敗!";
        }else if(exception instanceof BadCredentialsException){
            str = "用戶名或密碼錯誤, 登入失敗!";
        }else if(exception instanceof CredentialsExpiredException){
            str = "密碼過期, 登入失敗!";
        }else if(exception instanceof DisabledException){
            str = "帳號被禁用, 登入失敗!";
        }else if(exception instanceof LockedException){
            str = "帳號被鎖住, 登入失敗!";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            str = "帳號不存在, 登入失敗!";
        }else if(exception instanceof CustomerAuthException) {
            //token 驗證失敗
            code = 600;
            str = exception.getMessage();
        }else{
            str = "登入失敗!";
        }

        //返回格式設定
        String res = JSONObject.toJSONString(ResultObject.createInstance(false,str));
        out.write(res.getBytes());
        out.flush();
        out.close();
    }
}
