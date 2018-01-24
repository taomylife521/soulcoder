/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.service.impl;

import com.soulcoder.common.utils.EntityToMapUtils;
import com.soulcoder.dao.SysRoleDao;
import com.soulcoder.requestdto.Req_RoleList;
import com.soulcoder.responsedto.Res_RoleList;
import com.soulcoder.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Aministrator on 2018-01-22.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private SysRoleDao roleDao;
    public void correlationPermissions(Long roleId, Long... permissionIds) {

    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {

    }

    public Res_RoleList queryList(Req_RoleList request) {
        Map<String,Object> map= EntityToMapUtils.convertToMap(request);

        return null;
    }
}
