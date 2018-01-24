/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-22.
 */
public class Req_RoleList extends RequestBase {

    /**
    * @Author:Aministrator
    * @Description:角色名称
    * @Date:2018-01-22
    * @param
    * @return
    */
    @JSONField(name="rolename")
    private String roleName;

    /**
    * @Author:Aministrator
    * @Description:部门id
    * @Date:2018-01-22
    * @param
    * @return
    */
    @JSONField(name="deptid")
    private Integer deptId;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
