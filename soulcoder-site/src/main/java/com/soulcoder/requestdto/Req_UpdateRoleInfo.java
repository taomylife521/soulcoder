package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;
import com.soulcoder.requestdto.dtomodel.RoleInfo;

import javax.validation.constraints.NotNull;

/**
 * Created by Aministrator on 2018-01-27.
 */
public class Req_UpdateRoleInfo extends RoleInfo {
    @NotNull
    @JSONField(name="roleid")
    public String roleId;
}
