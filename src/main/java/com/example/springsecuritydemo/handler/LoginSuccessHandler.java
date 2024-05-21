package com.example.springsecuritydemo.handler;

import com.alibaba.fastjson2.JSONObject;
import com.example.springsecuritydemo.model.SysMyUser;
import com.example.springsecuritydemo.util.RedisCache;
import com.example.springsecuritydemo.util.TokenUtil;
import com.example.springsecuritydemo.vo.LoginResultObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 認證成功處理
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedisCache redisCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("以進來LoginSuccessHandler");

        SysMyUser user = (SysMyUser) authentication.getPrincipal();

        // 登入成功, 先產生 Token
        String token = TokenUtil.genAccessToken(user.getUsername());

        // 取 token 過期時間
        long expireTime = TokenUtil.getExpirationTime(token);

        //返回前端的 token 訊息
        LoginResultObject loginResultObject = new LoginResultObject();
        loginResultObject.setUserInfo(user);
        loginResultObject.setCode(200L); // 狀態碼

        //把 token 放到 redis 裡面, 登出跟修改密碼要清空 token 取的時候也從 redis
        redisCache.setCacheObject(request.getRemoteAddr(), token, TokenUtil.ACCESS_EXPIRE, TimeUnit.MILLISECONDS);

        // VO 裡面放 token 跟過期時間
        loginResultObject.setToken(token);
        loginResultObject.setExpireTime(expireTime);

        String res = JSONObject.toJSONString(loginResultObject);
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(res.getBytes());
        out.flush();
        out.close();
    }
}
