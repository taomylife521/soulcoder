package com.soulcoder.service.impl;

import com.soulcoder.dao.SysRoleDeptDao;
import com.soulcoder.service.IRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aministrator on 2018-02-02.
 */
@Service("roleDeptService")
public class RoleDeptService implements IRoleDeptService {

    @Autowired
   private SysRoleDeptDao sysRoleDeptDao;



    public List<Integer> queryDeptIdList(Integer roleId) {
     return sysRoleDeptDao.queryDeptIdList(roleId);
    }

    public Boolean saveOrUpdateRoleDataTree(Integer roleId, List<Integer> deptIdList) {
        return null;
    }
}
