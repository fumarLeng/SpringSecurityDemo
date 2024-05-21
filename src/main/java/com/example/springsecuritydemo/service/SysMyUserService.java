package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.mapper.SysMyUserMapper;
import com.example.springsecuritydemo.model.SysMyUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
public class SysMyUserService {

    @Resource
    private SysMyUserMapper userMapper;

    /**
     * 根據 username 拿到會員信息,還有權限
     */
    public SysMyUser getUserInfoByUserId(String username) {
        log.info("已進來SysMyUserService 的 getUserInfoByUserId 方法");

        // 根據 username 拿到會員信息
        SysMyUser userInfo = userMapper.getUserInfoByUserId(username);
        System.out.println(userInfo.toString());
        Assert.notNull(userInfo, "用戶不存在");
        // 根據 sid 拿到權限信息
        List<String> autorizedList = userMapper.getAutorizedListByUserId(userInfo.getSid());
        userInfo.setRoles(autorizedList);
        return userInfo;
    }


    /**
     * 拿到加密后的密碼 ,用 BCryptPasswordEncoder 加密 10次 生成密碼
     *
     * @param password 密碼
     * @return 加密后的密碼
     */
    public String getEncoderPassword(String password) {
        log.info("已進來SysMyUserService 的 getEncoderPassword 方法");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePassword = encoder.encode(password);
        log.info("加密過的密碼為 : {}", encodePassword);
        return encodePassword;
    }

}
