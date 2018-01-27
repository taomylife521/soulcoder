package com.soulcoder.service;

import com.soulcoder.pojo.SysRole;
import com.soulcoder.requestdto.Req_AddRoleInfo;
import com.soulcoder.requestdto.Req_UpdateRoleInfo;

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
     List<SysRole> queryList(Map<String,Object> map);

    /**
     * @Author:Aministrator
     * @Description:添加角色信息
     * @Date:2018-01-22
     * @param
     * @return
     */
     boolean save(Req_AddRoleInfo request);

    /**
     * @Author:Aministrator
     * @Description:添加角色信息
     * @Date:2018-01-22
     * @param
     * @return
     */
    boolean update(Req_UpdateRoleInfo request);
}
