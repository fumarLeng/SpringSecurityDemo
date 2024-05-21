package com.example.springsecuritydemo.handler;

import com.alibaba.fastjson2.JSONObject;
import com.example.springsecuritydemo.result.ResultObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
public class LoginAccessDefineHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("以進來LoginAccessDefineHandler");
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        String res = JSONObject.toJSONString(ResultObject.createInstance(false, 700, "權限不足"));
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
