package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.util.RedisCache;
import com.example.springsecuritydemo.util.TokenUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class RedisController {

    private final RedisCache redisCache;

    public RedisController(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @RequestMapping("/redis")
    public String getRedis() {
        redisCache.setCacheObject("test", "test");
        return redisCache.getCacheObject("test").toString();
    }

    @RequestMapping("/user")
    public String getUser() {
        String qqq = "134 ".trim();
        return "登入成功拿到User";
    }

    @RequestMapping("/jjwt")
    public Map<String, String> jjwt() {
        Map<String, String> map = new HashMap<>();
        String token = TokenUtil.genAccessToken("Leo");
        map.put("encoding", token);
        String qqq = "123 ".trim();
        return map;

    }

}
