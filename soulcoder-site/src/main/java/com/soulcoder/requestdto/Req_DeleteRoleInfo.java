package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;

/**
 * Created by Aministrator on 2018-01-29.
 */
public class Req_DeleteRoleInfo extends RequestBase {
    
    /**
    * 角色id
    */
    @NotNull
    @JSONField(name="roleid")
    public int roleId;
}
