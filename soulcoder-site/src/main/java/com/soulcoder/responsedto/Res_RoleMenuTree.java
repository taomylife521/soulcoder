package com.soulcoder.responsedto;

import com.alibaba.fastjson.annotation.JSONField;
import com.soulcoder.pojo.BaseEntity;
import com.soulcoder.pojo.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aministrator on 2018-01-30.
 */
public class Res_RoleMenuTree extends  ResponseBase{


    public List<SysMenu> getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(List<SysMenu> menuTree) {
        this.menuTree = menuTree;
    }





    /**
     * 角色对应的菜单权限列表
     */
    @JSONField(name="menutree")
    private List<SysMenu> menuTree = new ArrayList<SysMenu>();



    /**
    * 角色对应的部门id列表数据权限
    */
    @JSONField(name="datatree")
    private List<BaseEntity> dataTree = new ArrayList<BaseEntity>();

    public List<BaseEntity> getDataTree() {
        return dataTree;
    }

    public void setDataTree(List<BaseEntity> dataTree) {
        this.dataTree = dataTree;
    }
}
