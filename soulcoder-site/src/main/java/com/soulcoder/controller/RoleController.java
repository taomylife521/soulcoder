/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.controller;

import com.soulcoder.common.utils.EntityToMapUtils;
import com.soulcoder.common.utils.ValidatorUtils;
import com.soulcoder.common.validator.ModifyGroup;
import com.soulcoder.common.validator.SaveGroup;
import com.soulcoder.enums.ResponseStatus;
import com.soulcoder.pojo.SysDept;
import com.soulcoder.pojo.SysMenu;
import com.soulcoder.pojo.SysRole;
import com.soulcoder.requestdto.*;
import com.soulcoder.responsedto.R;
import com.soulcoder.responsedto.Res_QueryRoleTreeList;
import com.soulcoder.responsedto.Res_RoleMenuTree;
import com.soulcoder.responsedto.dtomodel.QueryRoleTreeDetail;
import com.soulcoder.service.IDeptService;
import com.soulcoder.service.IRoleMenuService;
import com.soulcoder.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Aministrator on 2018-01-19.
 * 权限标识为RequestMapping值连起来替换“/“ 为 ”.“
 * */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends  AbstractController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuService;

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
  // @RequiresPermissions("sys:role:list")
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

    /**
    * 获取角色对应的菜单和权限信息
    */
    @RequestMapping("rolemenutree")
    @ResponseBody
    public R roleMenuTree(@RequestBody Req_RoleMenuTree request){
        //校验实体
        R res = ValidatorUtils.validateEntity(request);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        List<SysMenu> sysMenuList= roleMenuService.roleMenuTree(request.roleId,request.deptId);//如果部门id不为空，则他可以查看到他本部门和所有其他子部门的角色和权限
        Res_RoleMenuTree roleMenuTree = new Res_RoleMenuTree();
        //for(SysMenu sysMenu; sysMenuList.iterator();)
//        List<SysMenuMapper> menuMapperList = new ArrayList<SysMenuMapper>();
//        for(Iterator<SysMenu> item=sysMenuList.iterator();item.hasNext();){
//            SysMenu sysMenu=item.next();
//            SysMenuMapper sysMenuMapper = new SysMenuMapper();
//            sysMenuMapper.setCreateby(sysMenu.getCreateby());
//            sysMenuMapper.setCreatetime(sysMenu.getCreatetime());
//            sysMenuMapper.setIcon(sysMenu.getIcon());
//            sysMenuMapper.setId(sysMenu.getId());
//            sysMenuMapper.setIsdel(sysMenu.getIsdel());
//            sysMenuMapper.setList(sysMenu.getList());
//            sysMenuMapper.setName(sysMenu.getName());
//            sysMenuMapper.setParentid(sysMenu.getParentid());
//            sysMenuMapper.setPermissions(sysMenu.getPermissions());
//            sysMenuMapper.setSortnum(sysMenu.getSortnum());
//            sysMenuMapper.setType(sysMenu.getType());
//            sysMenuMapper.setUrl(sysMenu.getUrl());
//            sysMenuMapper.setChkDisabled(true);
//            menuMapperList.add(sysMenuMapper);
//        }
        roleMenuTree.setMenuTree(sysMenuList);
        return R.ok(roleMenuTree);
    }

    /**
    * 修改角色信息
    */
    @RequestMapping(value="modify",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys.role.modify")
    public R modify(@RequestBody Req_UpdateRoleInfo request){
        //校验实体
        R res = ValidatorUtils.validateEntity(request, ModifyGroup.class);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        //先查询当前部门下有没有该角色，有的话直接返回失败，没有则添加
        Req_RoleList req = new Req_RoleList();
        req.setRoleName(request.roleName);
        req.setDeptId(request.roleDeptId);
        List<SysRole> roleList = roleService.queryList(EntityToMapUtils.convertToMap(req));
        if(roleList.size() <= 0){
            return R.failed("当前部门下已不存在该角色");
        }
        boolean r=roleService.update(request);
        if(r) {
            return R.ok();
        }
        return R.failed("更新失败");
    }

    
    /**
    * 添加角色信息
    */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
//    @RequiresPermissions("sys.role.save")
    public R save(@RequestBody  Req_AddRoleInfo request){
        //校验实体
        R res = ValidatorUtils.validateEntity(request, SaveGroup.class);
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

    /**
     * 更新角色菜单信息，统一先删除，再添加
     */
    @RequestMapping(value = "updaterolemenutree",method = RequestMethod.POST)
    @ResponseBody
   // @RequiresPermissions("sys.role.updaterolemenutree")
    public R updaterolemenutree(@RequestBody Req_UpdateRoleMenuTree request){
        //校验实体
        R res = ValidatorUtils.validateEntity(request, SaveGroup.class);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        //先查询当前部门下有没有该角色，有的话直接返回失败，没有则添加
        Req_RoleList req = new Req_RoleList();
        req.setRoleId(request.roleId);
        req.setDeptId(request.deptId);
        List<SysRole> roleList = roleService.queryList(EntityToMapUtils.convertToMap(req));
        if(roleList.size() <= 0){
            return R.failed("当前部门下不存在该角色,请先添加该角色再授权!");
        }
        boolean r= roleMenuService.saveOrUpdateRoleMenuTree(request.roleId,request.menuIdList);
        if(!r){
            return R.failed("更新失败");
        }
        return R.ok();
    }

    /**
    * 删除角色
    */
    @RequestMapping("/delete")
    @ResponseBody
//    @RequiresPermissions("sys.role.delete")
    public R delete(@RequestBody Req_DeleteRoleInfo request){
        //校验实体
        R res = ValidatorUtils.validateEntity(request);
        if (res.getStatus() != ResponseStatus.Success.getIndex()) {
            return res;
        }
        //先查询当前部门下有没有该角色，有的话直接返回失败，没有则添加
        Req_RoleList req = new Req_RoleList();
        req.setRoleId(request.roleId);
        req.setDeptId(request.deptId);
        List<SysRole> roleList = roleService.queryList(EntityToMapUtils.convertToMap(req));
        if(roleList.size() <= 0){
            return R.failed("当前部门下已不存在该角色!");
        }
        boolean r=roleService.delete(request.roleId);
        if(r){
            return R.ok();
        }
       return R.failed("删除失败");
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
        roleTreeDetail.setIsleaf(false);
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
        roleTreeDetail.setIsleaf(true);
        return roleTreeDetail;
    }



}
