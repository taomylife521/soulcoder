/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.service.impl;

import com.soulcoder.common.utils.EntityToMapUtils;
import com.soulcoder.dao.SysDeptDao;
import com.soulcoder.pojo.SysDept;
import com.soulcoder.requestdto.Req_QueryDeptList;
import com.soulcoder.requestdto.Req_QueryDeptRoleList;
import com.soulcoder.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aministrator on 2018-01-15.
 */
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private SysDeptDao deptDao;
    /**
     * 根据部门id获取子部门
     * */
    public String getSubDeptIdList(int deptId) {
        //部门及子部门ID列表
        List<Integer> deptIdList = new ArrayList<Integer>();
        List<Integer> subDeptIdList= deptDao.getSubDeptIdList(deptId);//获取子部门id
        getDeptTreeList(subDeptIdList,deptIdList);

        deptIdList.add(deptId);//添加本部门
        String deptFilter = StringUtils.join(deptIdList,",");
        return deptFilter;

    }

    /**
    * @Author:Aministrator
    * @Description:查询部门列表
    * @Date:2018-01-17
    * @param
    * @return
    */
    public List<SysDept> queryDeptList(Req_QueryDeptList request) {
        Map<String, Object> map = EntityToMapUtils.convertToMap(request);//转换实体到map
       return deptDao.queryList(map);
    }

    /**
    * 查询部门对应的角色列表
    */
    public List<SysDept> queryDeptRoleList(Req_QueryDeptRoleList request) {
        return null;
    }


    /**
    * @Author:Aministrator
    * @Description:递归
    * @Date:2018-01-15
    * @param
    * @return
    */
    private void getDeptTreeList(List<Integer> subDeptIdList, List<Integer> deptIdList) {
        for (Integer deptId:subDeptIdList) {
           List<Integer> list= deptDao.getSubDeptIdList(deptId);
           if(list.size() >0){
               getDeptTreeList(list,deptIdList);
           }
           deptIdList.add(deptId);
        }
    }
}
