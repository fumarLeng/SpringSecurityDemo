package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.SysMyPermission;
import com.example.springsecuritydemo.model.SysMyUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("customerUserDetailsService")
public class CustomerUserDetailsService implements UserDetailsService {

    @Resource
    private SysMyUserService userService;
    @Resource
    private SysMyPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("已進來CustomerUserDetailsService 的 loadUserByUsername 方法");

        SysMyUser user = userService.getUserInfoByUserId(username);

        //如果不存在要丟例外
        if (user == null) {
            throw new UsernameNotFoundException("用戶名或密碼錯誤");
        }

        //根據用戶id查詢用戶權限
        List<SysMyPermission> permissionList = permissionService.getPermissionListByUserId(username);
        //取出權限中配置的permissionCode
        List<String> collect = permissionList.stream().filter(item -> item != null)
                .map(item -> item.getPermissionCode())
                .filter(item -> item != null)
                .collect(Collectors.toList());

        //收集到的 permissionCode ,轉成 String[] ,再轉成 Security 的 GrantedAuthority
        String[] strings = collect.toArray(new String[collect.size()]);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);
        // 設定權限
        user.setAuthorities(authorityList);
        // 配置權限詳細訊息
        user.setPermissionList(permissionList);

        return user;

    }
}
