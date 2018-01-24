/*
 * Copyright (c) 2017.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�
 */

package com.soulcoder.controller;



import com.soulcoder.partener.shiro.ShiroUtils;
import com.soulcoder.requestdto.Req_LoginRequest;
import com.soulcoder.responsedto.R;
import org.apache.shiro.authc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * The type Login controller.
 */
@Controller
public class LoginController extends AbstractController {

	/**
	 * The Logger.
	 */
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Login string.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return the string
	 */
	@RequestMapping(name="/login")//,produces="application/json"
	public String Login(String userName,String password)
	{
//		java.util.List<Cookie> cookies=Arrays.asList( this.getRequest().getCookies());
//		String contextPath=this.getRequest().getContextPath();
//		Map<String, String[]> map = new HashMap<String, String[]>();
//		map.put("123", new String[]{"456"});
//		
//		 map.putAll(this.getRequest().getParameterMap());
		logger.error("i am error");
		logger.info("i am info");

//		logger.debug("i am debug");
//		logger.trace("i am trace");
		 return "login";
//		if(map.size()<=0)
//		{
//			map.put("123", new String[]{"456"});
//		}
		//return map;
//		 StringBuilder stringBuilder = new StringBuilder();
//		for (Cookie item : cookies) {
    //			stringBuilder.append("name:"+item.getName()+",value:"+item.getValue()+",version:"+item.getVersion());
//			stringBuilder.append("\n");
//		}
//		return stringBuilder;
	}

	/**
	 * Login r.
	 *
	 * @param request the request
	 * @return the r
	 */
	@RequestMapping(name="/login",method=RequestMethod.POST)//,produces="application/json"
	@ResponseBody
	public R Login(Req_LoginRequest request)
	{
		try {
			HashMap<String,String> map = new HashMap<String, String>();
			if(request.getUserName().length()<=0 || request.getPassword().length()<=0)
			{
				return  R.failed("登录失败", null);
			}

			//密码校验
			UsernamePasswordToken token = new UsernamePasswordToken(request.getUserName(),request.getPassword());
		    ShiroUtils.getSubject().login(token);
			return  R.ok();
			
		} catch (UnknownAccountException e) {
			return  R.exception(e.getMessage(),e);
		}catch (IncorrectCredentialsException e) {
			return  R.exception("账号或密码不正确",e);
		}catch (LockedAccountException e) {
			return  R.exception("账号已被锁定,请联系管理员",e);
		}catch (AuthenticationException e) {
			return  R.exception("账户验证失败",e);
		}

	}
}
