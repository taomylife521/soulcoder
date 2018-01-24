package com.soulcoder.service;

import com.soulcoder.requestdto.Req_RoleList;
import com.soulcoder.responsedto.Res_RoleList;

/**
 * The interface Role service.
 */
public interface IRoleService {
    /**
     * Correlation permissions.
     *
     * @param roleId        the role id
     * @param permissionIds the permission ids
     */
//根据角色id分配权限
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * Uncorrelation permissions.
     *
     * @param roleId        the role id
     * @param permissionIds the permission ids
     */
//解除角色id对应的权限
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);//


    /**
    * @Author:Aministrator
    * @Description:角色列表
    * @Date:2018-01-22
    * @param
    * @return
    */
    public Res_RoleList queryList(Req_RoleList request);
}
