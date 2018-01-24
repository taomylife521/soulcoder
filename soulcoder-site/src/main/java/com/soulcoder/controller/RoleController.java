/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.controller;

import com.soulcoder.common.utils.EntityToMapUtils;
import com.soulcoder.common.utils.ValidatorUtils;
import com.soulcoder.enums.ResponseStatus;
import com.soulcoder.pojo.SysDept;
import com.soulcoder.pojo.SysRole;
import com.soulcoder.requestdto.Req_QueryDeptList;
import com.soulcoder.requestdto.Req_RoleList;
import com.soulcoder.responsedto.R;
import com.soulcoder.responsedto.Res_QueryDeptList;
import com.soulcoder.service.IDeptService;
import com.soulcoder.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Aministrator on 2018-01-19.
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends  AbstractController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IDeptService deptService;

    @RequestMapping("index")
    public String index()
    {
        return "sys/sysrole";
    }

    /**
    * @Author:Aministrator
    * @Description:角色列表
    * @Date:2018-01-22
    * @param
    * @return
    */
    @RequestMapping("list")
    public R list(@RequestBody Req_RoleList request) {
        //校验实体
        R res = ValidatorUtils.validateEntity(request);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        //先查询部门列表
        Req_QueryDeptList queryDeptList = new Req_QueryDeptList();
        queryDeptList.setDeptId(request.getDeptId());
        List<SysDept> deptList = deptService.queryDeptList(queryDeptList);
        for (Iterator<SysDept> item = deptList.iterator(); item.hasNext(); ) {//挨个遍历部门获取角色
            Req_RoleList req = new Req_RoleList();
            SysDept deptItem = item.next();
            req.setDeptId(deptItem.getId());
            req.setRoleName(request.getRoleName());
            List<SysRole> roleList = roleService.queryList(EntityToMapUtils.convertToMap(req));//查询列表得到当前部门下对应的角色
            deptItem.setRoleList(roleList);
        }
        Res_QueryDeptList queryDeptList2 = new Res_QueryDeptList();
        queryDeptList2.setDeptList(deptList);
        return R.ok(queryDeptList2);//返回部门列表和角色列表
    }



}
