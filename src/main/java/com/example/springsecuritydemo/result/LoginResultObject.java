package com.example.springsecuritydemo.result;

import com.example.springsecuritydemo.model.SysMyUser;
import lombok.Data;

@Data
public class LoginResultObject {

    private String token;

    /**
     * token 過期時間
     */
    private Long expireTime;
    private SysMyUser userInfo;
    private Long code;
}
