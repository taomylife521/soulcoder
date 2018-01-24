/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.responsedto;

import com.soulcoder.pojo.SysRole;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-22.
 */
public class Res_RoleList extends ResponseBase {
        public List<SysRole> roleList;

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
