package com.soulcoder.service;


import com.soulcoder.pojo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * The interface User service.
 */
public interface IUserService {


    /**
     * Find by username sys user.
     *
     * @param username the username
     * @return the sys user
     */
     SysUser findByUserName(String username);


   /**
   * @Author:Aministrator
   * @Description:根据用户id获取菜单列表
   * @Date:10:32 2018-01-09
   * @param
   * @return
   */
    List<Integer> getUserMenuIdList(Integer userId);

    /**
     * @Author:Aministrator
     * @Description:更新密码
     * @Date:10:32 2018-01-09
     * @param
     * @return
     */
    Boolean updatePassword(Integer userId,String password,String newPassword);

    /**
     * 查询用户列表
     */
    List<SysUser> queryList(Map<String, Object> map);

    /**
     * 查询总数
     * */
   Integer queryTotal(Map<String, Object> map);

    /**
     * 查询总数
     * */
    void save(SysUser user);

}
