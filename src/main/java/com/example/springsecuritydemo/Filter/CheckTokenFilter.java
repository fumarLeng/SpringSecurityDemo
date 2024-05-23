package com.example.springsecuritydemo.Filter;

import com.example.springsecuritydemo.exception.CustomerAuthException;
import com.example.springsecuritydemo.handler.LoginFiledHandler;
import com.example.springsecuritydemo.service.CustomerUserDetailsService;
import com.example.springsecuritydemo.util.RedisCache;
import com.example.springsecuritydemo.util.TokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Data
@Component("checkTokenFilter")
@EqualsAndHashCode(callSuper = false)
public class CheckTokenFilter extends OncePerRequestFilter {

    @Value("${Leo.login.url}")
    private String loginUrl;

    private LoginFiledHandler loginFilterHandler;
    private CustomerUserDetailsService customerUserDetailsService;

    @Resource
    private RedisCache redisCache;

    public CheckTokenFilter(LoginFiledHandler loginFilterHandler, CustomerUserDetailsService customerUserDetailsService) {
        this.loginFilterHandler = loginFilterHandler;
        this.customerUserDetailsService = customerUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("以進來doFilterInternal");
        //拿到請求的 url
        String url = httpServletRequest.getRequestURI();
        if (StringUtils.contains(httpServletRequest.getServletPath(), "swagger")
                || StringUtils.contains(httpServletRequest.getServletPath(), "webjars")
                || StringUtils.contains(httpServletRequest.getServletPath(), "v3")
                || StringUtils.contains(httpServletRequest.getServletPath(), "profile")
                || StringUtils.contains(httpServletRequest.getServletPath(), "swagger-ui")
                || StringUtils.contains(httpServletRequest.getServletPath(), "swagger-resources")
                || StringUtils.contains(httpServletRequest.getServletPath(), "csrf")
                || StringUtils.contains(httpServletRequest.getServletPath(), "favicon")
                || StringUtils.contains(httpServletRequest.getServletPath(), "v2")
                || StringUtils.contains(httpServletRequest.getServletPath(), "user")
                || StringUtils.contains(httpServletRequest.getServletPath(), "getImageCode")) {

            filterChain.doFilter(httpServletRequest, httpServletResponse);

        } else if (StringUtils.equals(url, loginUrl)) {
            // 登入請求放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            try {

                //token验证（如果不是登录请求 验证 Token）
                if (!url.equals(loginUrl)) {
                    validateToken(httpServletRequest);
                }
            } catch (AuthenticationException e) {
                loginFilterHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void validateToken(HttpServletRequest httpServletRequest) {

        //從請求的 Header 取 Token , 如果沒有就去參數中取
        String token = httpServletRequest.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            token = httpServletRequest.getParameter("token");
        }

        //如果請求也沒有, 那就從 redis 裡面用 addr 取
        if (StringUtils.isEmpty(token)) {
            token = redisCache.getCacheObject(httpServletRequest.getRemoteAddr());
        }

        if (StringUtils.isEmpty(token)) {
            throw new CustomerAuthException("Token不存在!");
        }

        //解析 token
        String username = TokenUtil.getUserFromToken(token);
        if (StringUtils.isEmpty(username)) {
            throw new CustomerAuthException("token解析失敗!");
        }
        //拿到用戶訊息
        UserDetails user = customerUserDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new CustomerAuthException("token驗證失敗!");
        }
        //new出一個用戶訊息跟權限的 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // 使用 WebAuthenticationDetailsSource 把请求中拿到的详细訊息放入 authenticationToken 裡面
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        //再放到 securityContext 裡面, 這樣後面可以直接從 SecurityContext 拿到訊息跟權限
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
