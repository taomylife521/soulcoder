/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.responsedto;

import com.alibaba.fastjson.annotation.JSONField;
import com.soulcoder.pojo.SysDept;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-17.
 */
public class Res_QueryDeptList extends  ResponseBase {
    public List<SysDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<SysDept> deptList) {
        this.deptList = deptList;
    }

    @JSONField(name="deptlist")
    private List<SysDept> deptList;
}
