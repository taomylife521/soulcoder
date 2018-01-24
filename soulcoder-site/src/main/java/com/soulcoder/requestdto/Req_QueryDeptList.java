/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-17.
 */
public class Req_QueryDeptList extends  RequestBase {

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
    * @Author:Aministrator
    * @Description:部门名称
    * @Date:2018-01-17
    * @param
    * @return
    */
    @JSONField(name="deptname")
    private String deptName="";

}
