package com.example.springsecuritydemo.handler;

import com.alibaba.fastjson2.JSONObject;
import com.example.springsecuritydemo.result.ResultObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 匿名用戶處理
 * 登入沒有認證的話, Security 會自動調用這邊的 commence 方法
 */
@Slf4j
@Component
public class LoginAuthHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("以進來LoginAuthHandler");
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String res = JSONObject.toJSONString(ResultObject.createInstance(false, 600, "匿名用戶沒有權限訪問"));
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();

    }
}
