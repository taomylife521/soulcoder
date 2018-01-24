package com.soulcoder.service.impl;

import com.soulcoder.common.annotation.DataFilter;
import com.soulcoder.common.utils.EntityToMapUtils;
import com.soulcoder.dao.SysUserDao;
import com.soulcoder.pojo.SysUser;
import com.soulcoder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User service.
 */
@Service("userService")
public class UserServiceImpl implements IUserService{

	@Autowired
	private SysUserDao userDao;

    /**
    * @Author:Aministrator
    * @Description:根据用户名查询用户实体
    * @Date:2018-01-09
    * @param
    * @return
    */
	public SysUser findByUserName(String username) {
		return userDao.queryByUsername(username);
	}

	/**
	* @Author:Aministrator
	* @Description:根据用户id获取用户菜单列表
	* @Date:2018-01-09
	* @param
	* @return
	*/
	public List<Integer> getUserMenuIdList(Integer userId) {
		return userDao.queryMenuIdListByUserId(userId);
	}

	/**
	* @Author:Aministrator
	* @Description:更新密码
	* @Date:2018-01-10
	* @param
	* @return
	*/
	public Boolean updatePassword(Integer userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		Integer r=userDao.updatePassword(map);
		if(r<=0){
			return false;
		}
		return true;
	}

	/**
	* @Author:Aministrator
	* @Description:查询用户列表
	* @Date:2018-01-15
	* @param
	* @return
	*/
	@DataFilter(tableAlias = "u", user = false)
	public List<SysUser> queryList(Map<String, Object> map) {
		return userDao.queryList(map);
	}

	public Integer queryTotal(Map<String, Object> map) {
		return userDao.queryTotal(map);
	}
}
