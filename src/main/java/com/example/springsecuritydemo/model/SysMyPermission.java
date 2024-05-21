package com.example.springsecuritydemo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysMyPermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * sid
     */
    @TableId
    private String sid;

    /**
     * parent_id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * parent_name
     */
    @TableField("parent_name")
    private String parentName;

    /**
     * permission_name
     */
    @TableField("permission_name")
    private String permissionName;

    /**
     * permission_code
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * router_path
     */
    @TableField("router_path")
    private String routerPath;

    /**
     * router_name
     */
    @TableField("router_name")
    private String routerName;

    /**
     * auth_url
     */
    @TableField("auth_url")
    private String authUrl;

    /**
     * order_no
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * type
     */
    @TableField("type")
    private String type;

    /**
     * icon
     */
    @TableField("icon")
    private String icon;

    /**
     * remark
     */
    @TableField("remark")
    private String remark;

    /**
     * insert_user
     */
    @TableField("insert_user")
    private String insertUser;

    /**
     * insert_time
     */
    @TableField("insert_time")
    private String insertTime;

    /**
     * update_user
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * update_time
     */
    @TableField("update_time")
    private String updateTime;

    /**
     * license_code
     */
    @TableField("license_code")
    private String licenseCode;


    /**
     * 菜单的子集合
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SysMyPermission> children = new ArrayList<>();
}
