/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.dao;

import com.soulcoder.pojo.SysMenu;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-08.
 */
public interface SysMenuDao extends BaseDao<SysMenu>{
    /**
    * @Author:Aministrator
    * @Description:根据父菜单id查询子列表
    * @Date:17:58 2018-01-08
    * @param
    * @return
    */
     List<SysMenu> queryListByParentId(Integer parentId);

    /**
     * 根据用户id查询菜单列表
     *
     * @return list
     */
    List<SysMenu> queryMenuList(Object[] menuIdList);
}
