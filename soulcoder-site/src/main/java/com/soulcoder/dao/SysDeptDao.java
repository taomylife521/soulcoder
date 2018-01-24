/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.dao;

import com.soulcoder.pojo.SysDept;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-15.
 */
public interface SysDeptDao extends  BaseDao<SysDept> {
    /**
     * 根据部门id获取子部门id
     * */
    public List<Integer> getSubDeptIdList(int deptId);
}
