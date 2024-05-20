package com.example.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecuritydemo.model.SysMyUser;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMyUserMapper extends BaseMapper<SysMyUser> {
    /**
     * 根据用户名账号获取用户信息
     *
     * @param username 用户信息
     * @return 用户信息
     */
    SysMyUser getUserInfoByUserId(@Param("username") String username);

    /**
     * 根据用户id获取用户具备的权限信息
     *
     * @param sid 用户id
     * @return 用户具备的权限信息
     */
    List<String> getAutorizedListByUserId(@Param("sid") String sid);

}
