package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;

/**
 * Created by Aministrator on 2018-01-26.
 */
public class Req_UpdateRole extends  RequestBase {

   @NotNull
   @JSONField(name="rolename")
    public String roleName;

    @JSONField(name="roledescription")
    public String roleDescription;

 @NotNull
    @JSONField(name="roledeptid")
    public String roleDeptId;

 @NotNull
    @JSONField(name="ordernum")
    public String orderNum;

 @NotNull
    @JSONField(name="roleid")
    public String roleId;
}
