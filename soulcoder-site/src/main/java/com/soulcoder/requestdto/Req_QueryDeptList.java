/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-17.
 */
public class Req_QueryDeptList extends  RequestBase {


    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
    * @Author:Aministrator
    * @Description:部门名称
    * @Date:2018-01-17
    * @param
    * @return
    */
    @JSONField(name="deptid")
    private Integer deptId=-1;


}
