/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.controller;

import com.soulcoder.pojo.SysDept;
import com.soulcoder.requestdto.Req_QueryDeptList;
import com.soulcoder.responsedto.R;
import com.soulcoder.responsedto.Res_QueryDeptList;
import com.soulcoder.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Aministrator on 2018-01-17.
 */
@Controller
@RequestMapping("sys/dept")
public class DeptController extends  AbstractController {

    @Autowired
    private IDeptService deptService;

    /**
    * @Author:Aministrator
    * @Description:获取部门列表
    * @Date:2018-01-17
    * @param
    * @return
    */
    @RequestMapping("list")
    @ResponseBody
    public R deptList(Req_QueryDeptList request){
        List<SysDept> deptList = deptService.queryDeptList(request);
        Res_QueryDeptList resQueryDeptList = new Res_QueryDeptList();
        resQueryDeptList.setDeptList(deptList);
        return  R.ok(resQueryDeptList);
    }
}
