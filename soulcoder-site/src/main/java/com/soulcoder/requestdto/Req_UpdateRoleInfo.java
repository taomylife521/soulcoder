package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;
import com.soulcoder.common.validator.ModifyGroup;
import com.soulcoder.requestdto.dtomodel.RoleInfo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Aministrator on 2018-01-27.
 */
public class Req_UpdateRoleInfo extends RoleInfo {
    @NotBlank(message = "角色id不能为空",groups= ModifyGroup.class)
    @JSONField(name="roleid")
    public String roleId;
}
