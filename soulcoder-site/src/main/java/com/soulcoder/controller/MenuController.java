/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder.controller;

import com.soulcoder.common.exceptions.SoulCoderException;
import com.soulcoder.pojo.SysMenu;
import com.soulcoder.responsedto.R;
import com.soulcoder.responsedto.Req_NavList;
import com.soulcoder.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-08.
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends  AbstractController {

    @Autowired
    private IMenuService sysMenuService;

    @RequestMapping("/navlist")
    public R navList() throws SoulCoderException {
        //throw new SoulCoderException("test");
      List<SysMenu> menuList= sysMenuService.queryUserMenuList(getUserId());
        Req_NavList navList= new Req_NavList();
        navList.setMenuList(menuList);
      return  R.ok(navList);
    }
}
