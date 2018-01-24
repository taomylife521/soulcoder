package com.soulcoder.controller;

import com.soulcoder.common.utils.EntityToMapUtils;
import com.soulcoder.common.utils.ValidatorUtils;
import com.soulcoder.partener.shiro.ShiroUtils;
import com.soulcoder.pojo.SysUser;
import com.soulcoder.requestdto.Req_AddUser;
import com.soulcoder.requestdto.Req_UpdatePassword;
import com.soulcoder.requestdto.Req_UserList;
import com.soulcoder.responsedto.PageInfoResponseBase;
import com.soulcoder.responsedto.R;
import com.soulcoder.responsedto.Res_UserList;
import com.soulcoder.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * The type User controller.
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends AbstractController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/index")
	public String index(){
		return "/sys/sysuser";//返回用户视图
	}

	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestBody Req_UserList request){//, BindingResult bindingResult
//		if(bindingResult.hasErrors()){
//			List<ObjectError> objectErrorList= bindingResult.getAllErrors();
//			// String.join(",",objectErrorList.toArray());//	objectErrorList.toArray();
//			String allErrors= StringUtils.join(objectErrorList,",");
//			return   R.failed(allErrors);
//		}
		//校验实体
		R res=ValidatorUtils.validateEntity(request);
		if(res.getStatus() == "0")
		{
			return res;
		}
		Map<String, Object> map= EntityToMapUtils.convertToMap(request);//实体转map
		List<SysUser> userListData=	userService.queryList(map);//查询用户列表
		int totalCount=	userService.queryTotal(map);//查询用户总数
		Res_UserList userList = new Res_UserList();
		userList.setUserList(userListData);
		PageInfoResponseBase<Res_UserList> pageInfoResponseBase = new PageInfoResponseBase<Res_UserList>();
		pageInfoResponseBase.setDraw(request.getDraw());
		pageInfoResponseBase.setRecordsFiltered(totalCount);
		pageInfoResponseBase.setRecordsTotal(totalCount);
		pageInfoResponseBase.setData(userList);
		//return pageInfoResponseBase;
		return   R.ok(pageInfoResponseBase);
	}

	/**
	* @Author:Aministrator
	* @Description:获取用户信息
	* @Date:10:27 2018-01-09
	* @param
	* @return
	*/
	@ResponseBody
	@RequestMapping("/userinfo")
	public R getUserInfo(){
		SysUser userInfo= getUser();
		if(userInfo!=null) {
			return  R.ok(userInfo);
		}
		return  R.failed("该用户不存在",null);
	}

	/**
	* @Author:Aministrator
	* @Description:修改用户密码
	* @Date:2018-01-10
	* @param
	* @return
	*/
	@ResponseBody
	@RequestMapping("/modifypassword")
	public R modifyPassword(@Validated  Req_UpdatePassword request, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()){
			List<ObjectError> objectErrorList= bindingResult.getAllErrors();
			String allErrors= StringUtils.join(objectErrorList,",");
			return  R.failed(allErrors);
		}
			//request.getPassword();
		//加密
		String password = ShiroUtils.sha256(request.getPassword(), getUser().getSalt());

		String newPassword = ShiroUtils.sha256(request.getNewpassword(), getUser().getSalt());
		Boolean r= userService.updatePassword(getUserId(),password,newPassword);
		if(!r)
		{
			return  R.failed("修改密码失败!");
		}
		return  R.ok();
	}


	/**
	* @Author:Aministrator
	* @Description:添加用户Get
	* @Date:2018-01-20
	* @param
	* @return
	*/
	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String addUser()
	{
		return "sys/adduser";
	}

	/**
	 * @Author:Aministrator
	 * @Description:添加用户Post
	 * @Date:2018-01-20
	 * @param
	 * @return
	 */
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public String addUser(Req_AddUser request)
	{
		return "sys/adduser";
	}
	
}
