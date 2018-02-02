package com.soulcoder.pojo;

/**
 * Created by Aministrator on 2018-02-02.
 */
public class SysRoleMenu extends BaseEntity {
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
    * 角色id
    */
    private Integer roleid;
    
    /**
    * 部门id
    */
    private Integer menuid;
}
