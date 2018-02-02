package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aministrator on 2018-02-02.
 */
public class Req_UpdateRoleMenuTree extends  RequestBase {

    @NotNull
    @JSONField(name="roleid")
    public Integer roleId;


    @NotNull
    @JSONField(name="deptid")
    public Integer deptId;

    /**
    * 角色对应的菜单权限
    */
    @NotNull
    @JSONField(name="menuidlist")
    public List<Integer> menuIdList = new ArrayList<Integer>();

    /**
    * 角色对应的数据权限
    */
    @NotNull
    @JSONField(name="deptidlist")
    public List<Integer> deptIdList=new ArrayList<Integer>();

}
