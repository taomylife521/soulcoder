package com.soulcoder.responsedto;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(name="menuTree")
    private List<SysMenu> menuTree = new ArrayList<SysMenu>();

}
