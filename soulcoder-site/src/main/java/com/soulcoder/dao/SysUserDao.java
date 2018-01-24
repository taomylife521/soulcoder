package com.soulcoder.dao;

import com.soulcoder.pojo.SysUser;
import com.soulcoder.requestdto.Req_UpdatePassword;

import java.util.List;
import java.util.Map;

/**
 * The interface Sys user dao.
 */
public interface SysUserDao extends BaseDao<SysUser> {
    /**
     * Query by username sys user.
     *
     * @param username the username
     * @return the sys user
     */
    public SysUser queryByUsername(String username);

    /**
     * Query by username sys user.
     *
     * @param username the username
     * @return the sys user
     */
    public List<Integer> queryMenuIdListByUserId(Integer userId);

    /**
     * @Author:Aministrator
     * @Description:更新密码
     * @Date:10:32 2018-01-09
     * @param
     * @return
     */
    Integer updatePassword(Map<String, Object> map);
}
