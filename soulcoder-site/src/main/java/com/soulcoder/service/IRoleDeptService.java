package com.soulcoder.service;

import java.util.List;

/**
 * Created by Aministrator on 2018-02-02.
 */
public interface IRoleDeptService {
    /**
    * 根据角色id获取部门数据权限树
    */
    List<Integer> queryDeptIdList(Integer roleId);
    
    /**
    * 根据角色id更新角色对应的部门数据权限树
    */
    Boolean saveOrUpdateRoleDataTree(Integer roleId,List<Integer> deptIdList);
}
