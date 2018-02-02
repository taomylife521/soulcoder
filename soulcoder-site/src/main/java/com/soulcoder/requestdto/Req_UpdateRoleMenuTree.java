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


    @NotNull
    @JSONField(name="menuidlist")
    public List<Integer> menuIdList = new ArrayList<Integer>();

}
