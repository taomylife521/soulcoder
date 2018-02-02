package com.soulcoder.pojo;

/**
 * Created by Aministrator on 2018-02-02.
 */
public class SysRoleDept extends  BaseEntity {
    private Integer roleid;

    private Integer deptid;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }
}
