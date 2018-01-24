/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-22.
 */
public class Req_QueryDeptRoleList extends RequestBase {
    
    /**
    * @Description:部门id
    */
    @JSONField(name="deptid")
    private Integer deptId;

    /**
    * 角色名称
    */
    @JSONField(name="rolename")
    private String roleName;
}
