/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.service.impl;

import com.soulcoder.common.annotation.DataFilter;
import com.soulcoder.dao.SysRoleDao;
import com.soulcoder.dao.SysRoleMenuDao;
import com.soulcoder.pojo.SysRole;
import com.soulcoder.requestdto.Req_AddRoleInfo;
import com.soulcoder.requestdto.Req_DeleteRoleInfo;
import com.soulcoder.requestdto.Req_UpdateRoleInfo;
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

    @Autowired
    private SysRoleMenuDao roleMenuDao;
    public void correlationPermissions(Long roleId, Long... permissionIds) {

    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {

    }

    @DataFilter(tableAlias = "r", user = false,isIgnoreDeptId = true)
    public List<SysRole> queryList(Map<String, Object> map) {
        return roleDao.queryList(map);
    }


    /**
    * @Author:Aministrator
    * @Description:保存角色信息
    * @Date:2018-01-27
    * @param
    * @return
    */
    public boolean save(Req_AddRoleInfo request) {

        SysRole role = new SysRole();
        role.setCreateby(request.loginId);
        role.setCreatetime(new Date());
        role.setDeptid(Integer.valueOf(request.roleDeptId));
        role.setDeptname(request.roleDeptName);
        role.setIsdel(0);
        role.setIsopen(true);
        role.setOrdernum(Integer.valueOf(request.orderNum));
        role.setRoledescription(request.roleDescription);
        role.setRolename(request.roleName);
        roleDao.save(role);//添加角色到部门
        return true;
    }


    /**
    * @Author:Aministrator
    * @Description:更新角色信息
    * @Date:2018-01-27
    * @param
    * @return
    */
    public boolean update(Req_UpdateRoleInfo request) {
       SysRole sysRole= roleDao.queryObject(request.roleId);
       if(sysRole == null){//说明不存在该角色信息
           return false;
       }
       sysRole.setRolename(request.roleName);
       sysRole.setRoledescription(request.roleDescription);
       sysRole.setOrdernum(request.orderNum);
       sysRole.setDeptname(request.roleDeptName);
       sysRole.setDeptid(request.roleDeptId);
     int r= roleDao.update(sysRole);
     if(r>0){
         return true;
     }
        return false;
    }

    /**
    * 删除角色
    */
    @Transactional
    public boolean delete(Integer roleId) {
      roleDao.delete(roleId);
      roleMenuDao.delete(roleId);
      return true;
    }


}
