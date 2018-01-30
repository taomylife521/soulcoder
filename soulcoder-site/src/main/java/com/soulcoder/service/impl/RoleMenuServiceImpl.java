package com.soulcoder.service.impl;

import com.soulcoder.dao.SysMenuDao;
import com.soulcoder.dao.SysRoleMenuDao;
import com.soulcoder.pojo.SysMenu;
import com.soulcoder.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Aministrator on 2018-01-30.
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Autowired
    private SysRoleMenuDao roleMenuDao;

    @Autowired
    private SysMenuDao menuDao;
    public List<SysMenu> roleMenuTree(Integer roleId,Integer deptId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("roleid",roleId);
        map.put("deptid",deptId);
        List<Integer> menuIdList= roleMenuDao.queryListByRoleId(map);//获取所有的菜单id，然后根据所有的菜单id，获取菜单列表
        if(menuIdList.size()<=0){
            return new ArrayList<SysMenu>() {};
        }

        return menuDao.queryMenuList(menuIdList.toArray());
    }
}