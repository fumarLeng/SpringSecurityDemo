package com.example.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecuritydemo.model.SysMyPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysMyPermissionMapper extends BaseMapper<SysMyPermission> {

    /**
     * 根据用户ID获取用户对应的权限
     * @param userId 用户ID
     * @return 权限列表
     */
    List<SysMyPermission> getMenuListByUserId(@Param("userId") String userId);

}
