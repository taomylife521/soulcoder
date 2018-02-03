/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-20.
 */
public class Req_AddUser extends  RequestBase {
    @JSONField(name = "realname")
    private String realName;

    @JSONField(name = "sex")
    private Integer sex;
    @JSONField(name = "userstatus")
    private Integer userStatus;
    @JSONField(name = "email")
    private String email;

    @JSONField(name = "birthday")
    private String birthday;

    @JSONField(name = "mobile")
    private String mobile;

    @JSONField(name = "deptid")
    private Integer deptId;

    @JSONField(name = "roleid")
    private Integer roleId;

    @JSONField(name = "imageurl")
    private String imageUrl;

    @JSONField(name = "qq")
    private String qq;

    @JSONField(name = "weixin")
    private String weixin;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "deptname")
    private String deptName;

    @JSONField(name = "rolename")
    private String roleName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String date) {
        this.birthday = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
