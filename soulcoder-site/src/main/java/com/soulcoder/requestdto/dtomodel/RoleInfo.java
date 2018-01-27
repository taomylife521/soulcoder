package com.soulcoder.requestdto.dtomodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.soulcoder.requestdto.RequestBase;

import javax.validation.constraints.NotNull;

/**
 * Created by Aministrator on 2018-01-27.
 */
public class RoleInfo extends RequestBase {
    @NotNull
    @JSONField(name="rolename")
    public String roleName;

    @JSONField(name="roledescription")
    public String roleDescription;

    @NotNull
    @JSONField(name="roledeptid")
    public Integer roleDeptId;

    @NotNull
    @JSONField(name="roledeptname")
    public String roleDeptName;

    @NotNull
    @JSONField(name="ordernum")
    public Integer orderNum;


}
