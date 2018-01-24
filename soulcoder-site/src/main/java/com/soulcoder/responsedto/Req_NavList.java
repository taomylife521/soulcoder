/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.responsedto;

import com.soulcoder.pojo.SysMenu;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-11.
 */
public class Req_NavList extends  ResponseBase {


    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }

    private List<SysMenu> menuList;
}
