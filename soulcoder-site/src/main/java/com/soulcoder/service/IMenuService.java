/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder.service;


import com.soulcoder.pojo.SysMenu;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-08.
 */
public interface IMenuService {
    /**
     * 根据用户id查询菜单列表
     *
     * @return list
     */
    List<SysMenu> queryUserMenuList(Integer userId);

    /**
     * 根据用户id查询菜单列表
     *
     * @return list
     */
    List<SysMenu> queryMenuList(List<Integer> menuIdList);
}
