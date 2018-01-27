/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.controller;

import com.soulcoder.common.utils.EntityToMapUtils;
import com.soulcoder.common.utils.ValidatorUtils;
import com.soulcoder.enums.ResponseStatus;
import com.soulcoder.pojo.SysDept;
import com.soulcoder.pojo.SysRole;
import com.soulcoder.requestdto.Req_AddRoleInfo;
import com.soulcoder.requestdto.Req_QueryDeptList;
import com.soulcoder.requestdto.Req_RoleList;
import com.soulcoder.requestdto.Req_UpdateRoleInfo;
import com.soulcoder.responsedto.R;
import com.soulcoder.responsedto.Res_QueryRoleTreeList;
import com.soulcoder.responsedto.dtomodel.QueryRoleTreeDetail;
import com.soulcoder.service.IDeptService;
import com.soulcoder.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public R list(Req_RoleList request) {
        //校验实体
        R res = ValidatorUtils.validateEntity(request);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        Res_QueryRoleTreeList queryRoleTreeList = new Res_QueryRoleTreeList();
        //先查询部门列表
        Req_QueryDeptList queryDeptList = new Req_QueryDeptList();
        queryDeptList.setDeptId(request.getDeptId());
        List<SysDept> deptList = deptService.queryDeptList(queryDeptList);
        for (Iterator<SysDept> item = deptList.iterator(); item.hasNext(); ) {//挨个遍历每个部门获取角色

            Req_RoleList req = new Req_RoleList();
            SysDept deptItem = item.next();
            QueryRoleTreeDetail roleTreeDetail = fillQueryRoleTreeDetail(deptItem);
            req.setDeptId(deptItem.getId());
            req.setRoleName(request.getRoleName());
            queryRoleTreeList.rolelist.add(roleTreeDetail);
            List<SysRole> roleList = roleService.queryList(EntityToMapUtils.convertToMap(req));//查询列表得到当前部门下对应的角色
            for(Iterator<SysRole> roleItem = roleList.iterator();roleItem.hasNext();){//把角色当成一个特殊的部门添加到部门列表中，形成树状结构
                SysRole role= roleItem.next();
                QueryRoleTreeDetail roleTreeDetail2 = fillQueryRoleTreeDetail(role,deptItem);
                queryRoleTreeList.rolelist.add(roleTreeDetail2);
            }
        }
        return R.ok(queryRoleTreeList);//返回部门列表和角色列表
    }

    @RequestMapping(value="modify",method = RequestMethod.POST)
    @ResponseBody
    public R modify(Req_UpdateRoleInfo request){
        //校验实体
        R res = ValidatorUtils.validateEntity(request);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        //先查询当前部门下有没有该角色，有的话直接返回失败，没有则添加
        Req_RoleList req = new Req_RoleList();
        req.setRoleName(request.roleName);
        req.setDeptId(request.roleDeptId);
        List<SysRole> roleList = roleService.queryList(EntityToMapUtils.convertToMap(req));
        if(roleList.size() > 0){
            return R.failed("当前部门下已存在该角色");
        }
        return R.ok();
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public R add(@RequestBody  Req_AddRoleInfo request){
        //校验实体
        R res = ValidatorUtils.validateEntity(request);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        //先查询当前部门下有没有该角色，有的话直接返回失败，没有则添加
        Req_RoleList req = new Req_RoleList();
        req.setRoleName(request.roleName);
        req.setDeptId(request.roleDeptId);
        List<SysRole> roleList = roleService.queryList(EntityToMapUtils.convertToMap(req));
        if(roleList.size() > 0){
            return R.failed("当前部门下已存在该角色");
        }
      boolean r= roleService.save(request);
        if(!r){
            return R.failed("添加失败");
        }
        return R.ok();
    }

    private QueryRoleTreeDetail fillQueryRoleTreeDetail(SysDept dept){
        QueryRoleTreeDetail roleTreeDetail = new QueryRoleTreeDetail();
        roleTreeDetail.setCreateby(dept.getCreateby());
        roleTreeDetail.setDescription(dept.getDeptdescription());
        roleTreeDetail.setId(dept.getId().toString());
        roleTreeDetail.setIsopen(dept.getIsopen());
        roleTreeDetail.setName(dept.getName());
        roleTreeDetail.setOrdernum(dept.getOrdernum());
        roleTreeDetail.setParentid(String.valueOf(dept.getParentid()));
        roleTreeDetail.setParentname(dept.getParentname());
        roleTreeDetail.setRole(false);
        return roleTreeDetail;
    }

    private QueryRoleTreeDetail fillQueryRoleTreeDetail(SysRole role,SysDept dept){
        QueryRoleTreeDetail roleTreeDetail = new QueryRoleTreeDetail();
        roleTreeDetail.setCreateby(role.getCreateby());
        roleTreeDetail.setDescription(role.getRoledescription());
        roleTreeDetail.setId("r_"+role.getId().toString());
        roleTreeDetail.setIsopen(role.getIsopen());
        roleTreeDetail.setName(role.getRolename());
        roleTreeDetail.setOrdernum(role.getOrdernum());
        roleTreeDetail.setParentid(String.valueOf(dept.getId()));
        roleTreeDetail.setParentname(dept.getName());
        roleTreeDetail.setRole(true);
        return roleTreeDetail;
    }



}
