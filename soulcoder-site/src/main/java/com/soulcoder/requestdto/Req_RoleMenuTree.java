package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-30.
 */
public class Req_RoleMenuTree extends  RequestBase {


    @JSONField(name="roleid")
    public Integer roleId;
    
    /**
    * 部门id，可以查看部门id 下所有角色对应的权限
    */
    @JSONField(name="deptid")
    public Integer deptId;
}
