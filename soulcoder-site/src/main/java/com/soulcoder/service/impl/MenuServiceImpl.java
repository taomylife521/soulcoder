/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.service.impl;

import com.soulcoder.dao.SysMenuDao;
import com.soulcoder.enums.Constant;
import com.soulcoder.pojo.SysMenu;
import com.soulcoder.service.IMenuService;
import com.soulcoder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aministrator on 2018-01-08.
 */
@Service
public class MenuServiceImpl implements IMenuService{

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private IUserService userService;

    /**
    * @Author:Aministrator
    * @Description:查询用户的菜单列表
    * @Date:16:36 2018-01-08
    * @param
    * @return
    */
    public List<SysMenu> queryUserMenuList(Integer userId) {
        if(userId == Constant.SUPER_ADMIN)//如果是超级管理员
        {
            return getAllMenuList(null);
        }
        //根据用户id获取用户的菜单列表
        List<Integer> menuIdList =userService.getUserMenuIdList(userId);
        return getAllMenuList(menuIdList);
    }

    /**
    * 根据菜单id列表查询菜单列表
    */
    public List<SysMenu> queryMenuList(List<Integer> menuIdList) {
       return sysMenuDao.queryMenuList(menuIdList.toArray());
    }

    /**
     * @Author:Aministrator
     * @Description:getallmenulist
     * @Date:16:34 2018-01-08
     * @param
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Integer> menuIdList)
    {
        //遍历
        List<SysMenu> menuList = queryListParentId(0, menuIdList);
        //递归
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }
     /**
     * @Author:Aministrator
     * @Description:根据父id查询子菜单
     * @Date:16:33 2018-01-08
     * @param
     * @return
     */
    private List<SysMenu> queryListParentId(Integer parentId,List<Integer> menuIdList){
        List<SysMenu> menuList = queryListParentId(parentId);
        if(menuIdList == null)
        {
            return menuList;
        }
        List<SysMenu> userMenuList = new ArrayList<SysMenu>();
         for (SysMenu menu:menuList)
         {
             if(menuIdList.contains(menu.getId()))
             {
                 userMenuList.add(menu);
             }
         }
         return userMenuList;


    }

    /**
    * @Author:Aministrator
    * @Description:根据父id查询子菜单
    * @Date:18:01 2018-01-08
    * @param
    * @return
    */
    private List<SysMenu> queryListParentId(Integer parentId){
        try {
            return sysMenuDao.queryListByParentId(parentId);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
    * @Author:Aministrator 
    * @Description: 递归菜单树
    * @Date:16:32 2018-01-08
    * @param 
    * @return 
    */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList,List<Integer> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for(SysMenu menu :menuList){
            if(menu.getType() == Constant.MenuType.CATALOG.getValue()){
               menu.setList(getMenuTreeList(queryListParentId(menu.getId(),menuIdList),menuIdList));

            }
            subMenuList.add(menu);
        }
        return subMenuList;
    }


    public List<SysMenu> queryList() {
        return sysMenuDao.queryList(null);
    }
}
