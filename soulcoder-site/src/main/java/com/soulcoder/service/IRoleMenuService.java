package com.soulcoder.service;

import com.soulcoder.pojo.SysMenu;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-30.
 */
public interface IRoleMenuService {
    List<SysMenu> roleMenuTree(Integer roleId,Integer deptId);
}
