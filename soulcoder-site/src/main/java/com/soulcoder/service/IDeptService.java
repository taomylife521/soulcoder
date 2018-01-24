/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.service;

import com.soulcoder.pojo.SysDept;
import com.soulcoder.requestdto.Req_QueryDeptList;
import com.soulcoder.requestdto.Req_QueryDeptRoleList;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-15.
 */
public interface IDeptService {

    /**
     * 根据部门id获取子部门id
     * */
    public String getSubDeptIdList(int deptId);

    public List<SysDept> queryDeptList(Req_QueryDeptList request);

    /**
     * 查询部门所对应的角色列表
     * */
    public List<SysDept> queryDeptRoleList(Req_QueryDeptRoleList request);


}
