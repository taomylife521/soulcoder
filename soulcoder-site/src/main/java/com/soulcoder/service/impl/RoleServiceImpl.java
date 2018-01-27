/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.service.impl;

import com.soulcoder.common.annotation.DataFilter;
import com.soulcoder.dao.SysRoleDao;
import com.soulcoder.pojo.SysRole;
import com.soulcoder.requestdto.Req_AddRoleInfo;
import com.soulcoder.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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

    @DataFilter(tableAlias = "r", user = false)
    public List<SysRole> queryList(Map<String, Object> map) {
        return roleDao.queryList(map);
    }


    public boolean save(Req_AddRoleInfo request) {

        SysRole role = new SysRole();
        role.setCreateby(request.loginId);
        role.setCreatetime(new Date());
        role.setDeptid(request.roleDeptId);
        role.setDeptname(request.roleDeptName);
        role.setIsdel(0);
        role.setIsopen(true);
        role.setOrdernum(request.orderNum);
        role.setRoledescription(request.roleDescription);
        role.setRolename(request.roleName);
        roleDao.save(role);//添加角色到部门
        return true;
    }


}
