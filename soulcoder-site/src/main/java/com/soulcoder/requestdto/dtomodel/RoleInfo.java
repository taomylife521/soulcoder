package com.soulcoder.requestdto.dtomodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.soulcoder.common.validator.ModifyGroup;
import com.soulcoder.common.validator.SaveGroup;
import com.soulcoder.requestdto.RequestBase;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Aministrator on 2018-01-27.
 */
public class RoleInfo extends RequestBase {
    @NotBlank(message = "角色名称不能为空",groups= {SaveGroup.class, ModifyGroup.class})
    @JSONField(name="rolename")
    public String roleName;

    @JSONField(name="roledescription")
    public String roleDescription;

    @NotBlank(message = "角色所在部门不能为空",groups= {SaveGroup.class, ModifyGroup.class})
    @JSONField(name="roledeptid")
    public Integer roleDeptId;

    @NotNull
    @JSONField(name="roledeptname")
    public String roleDeptName;

    @NotBlank(message = "角色排序号不能为空",groups= {SaveGroup.class, ModifyGroup.class})
    @JSONField(name="ordernum")
    public Integer orderNum;


}
