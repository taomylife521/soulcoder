/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.controller;

import com.soulcoder.requestdto.Req_RoleList;
import com.soulcoder.responsedto.R;
import com.soulcoder.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Aministrator on 2018-01-19.
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @RequestMapping("index")
    public String index()
    {
        return "sys/role";
    }

    /**
    * @Author:Aministrator
    * @Description:角色列表
    * @Date:2018-01-22
    * @param
    * @return
    */
    @RequestMapping("list")
    public R list(Req_RoleList request)
    {
        return  R.ok();
    }

}
