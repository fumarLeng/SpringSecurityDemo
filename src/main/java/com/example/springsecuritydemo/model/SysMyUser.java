package com.example.springsecuritydemo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class SysMyUser implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * sid
     */
    @TableId
    private String sid;

    /**
     * user_no
     */
    @TableField("user_no")
    private String userNo;

    /**
     * user_name
     */
    @TableField("user_name")
    private String userName;

    /**
     * password
     */
    @TableField("password")
    private String password;

    /**
     * nick_name
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * phone_number
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * email
     */
    @TableField("email")
    private String email;

    /**
     * department_id
     */
    @TableField("department_id")
    private String departmentId;

    /**
     * department_name
     */
    @TableField("department_name")
    private String departmentName;

    /**
     * is_admin
     */
    @TableField("is_admin")
    private String isAdmin;

    /**
     * sex
     */
    @TableField("sex")
    private String sex;

    /**
     * post_id
     */
    @TableField("post_id")
    private String postId;

    /**
     * post_name
     */
    @TableField("post_name")
    private String postName;

    /**
     * is_account_non_expired
     */
    @TableField("is_account_non_expired")
    private Boolean isAccountNonExpired;

    /**
     * is_account_non_locked
     */
    @TableField("is_account_non_locked")
    private Boolean isAccountNonLocked;

    /**
     * is_credentials_non_expired
     */
    @TableField("is_credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    /**
     * is_enabled
     */
    @TableField("is_enabled")
    private Boolean isEnabled;

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
     * 权限列表 就是菜单列表
     */
    @TableField(exist = false)
    private List<SysMyPermission> permissionList;
    /**
     * 认证信息 就是用户配置code
     */
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    /**
     * 用户权限信息
     */
    @TableField(exist = false)
    private List<String> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userNo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
