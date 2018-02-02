package com.soulcoder.service;

import com.soulcoder.pojo.SysMenu;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-30.
 */
public interface IRoleMenuService {
    /**
    * 查询某个部门角色对应的菜单树
    */
    List<SysMenu> roleMenuTree(Integer roleId);

    Boolean saveOrUpdateRoleMenuTree(Integer roleId,List<Integer> menuIdList);
}
