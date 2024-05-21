package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.mapper.SysMyPermissionMapper;
import com.example.springsecuritydemo.model.SysMyPermission;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Slf4j
@Service
public class SysMyPermissionService {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Resource
    private SysMyPermissionMapper sysMyPermissionMapper;

    /**
     * 根据用户id查询对应的权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    public List<SysMyPermission> getPermissionListByUserId(String userId) {
        log.info("已進來SysMyPermissionService 的 getPermissionListByUserIde 方法");
        log.info("userID {}", userId);
        List<SysMyPermission> sysMyPermissions = sysMyPermissionMapper.getMenuListByUserId(userId);
        log.info("getMenuListByUserId 回傳回來的 List : " + sysMyPermissions);

        // 根据用户ID获取用户对应的权限
        return sysMyPermissions;
    }
}
