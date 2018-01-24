/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by Aministrator on 2018-01-15.
 * 部门表
 */
public class SysDept extends  BaseEntity {


    /**
     * 上级id
     */
    private int parentid;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序号
     */
    private int ordernum;

    /**
     * 上级部门名称
     */
    private String parentname;

    /**
     * 是否展开
     */
    private Boolean isopen;

    private List<SysRole> roleList;

    /**
     * 创建时间
     */
    private transient Date createtime;

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }



    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public Boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * 是否删除
     */

    private transient Integer isdel;


}
