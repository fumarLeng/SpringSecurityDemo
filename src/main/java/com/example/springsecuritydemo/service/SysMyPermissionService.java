package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.mapper.SysMyPermissionMapper;
import com.example.springsecuritydemo.model.SysMyPermission;
import jakarta.annotation.Resource;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

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
        // 根据用户ID获取用户对应的权限
        return sysMyPermissionMapper.getMenuListByUserId(userId);
    }
}
