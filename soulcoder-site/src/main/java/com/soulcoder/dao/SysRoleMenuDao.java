package com.soulcoder.dao;

import com.soulcoder.pojo.SysRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * Created by Aministrator on 2018-01-30.
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {
    List<Integer> queryListByRoleId(Map<String,Object> map);

    /**
    * 删除该角色对应的所有菜单
    */
    void delete(Integer roleId);
}
