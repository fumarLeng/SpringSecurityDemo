package com.example.springsecuritydemo.vo;

import com.example.springsecuritydemo.model.SysMyUser;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResultObject {

    private SysMyUser userInfo;
    private Long code;
    private String token;
    private Long expireTime;
}
