package com.soulcoder.dao;

import com.soulcoder.pojo.SysRoleDept;

import java.util.List;

/**
 * Created by Aministrator on 2018-02-02.
 */
public interface SysRoleDeptDao extends   BaseDao<SysRoleDept> {
    List<Integer> queryDeptIdList(Integer roleId);
}
