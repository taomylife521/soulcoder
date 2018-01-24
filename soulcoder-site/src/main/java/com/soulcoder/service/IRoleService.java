package com.soulcoder.service;

import com.soulcoder.pojo.SysRole;

import java.util.List;
import java.util.Map;

/**
 * The interface Role service.
 */
public interface IRoleService {

    /**
    * @Author:Aministrator
    * @Description:角色列表
    * @Date:2018-01-22
    * @param
    * @return
    */
    public List<SysRole> queryList(Map<String,Object> map);
}
