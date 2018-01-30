package com.soulcoder.responsedto;

import com.alibaba.fastjson.annotation.JSONField;
import com.soulcoder.pojo.SysMenu;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-30.
 */
public class Res_RoleMenuTree extends  ResponseBase{

    @JSONField(name="menuTree")
    public List<SysMenu> menuTree;

}
