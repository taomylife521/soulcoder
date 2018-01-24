package com.soulcoder.partener.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soulcoder.dao.SysUserDao;
import com.soulcoder.pojo.SysUser;

/**
 * The type Custom realm.
 *
 * @author Aministrator
 */
@Component
public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private SysUserDao sysUserDao;
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 * 登录时调用
	 * object currentUser = SecurityUtils.getSubject();
      currentUser.login(token);
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken)token;
	    SysUser user= sysUserDao.queryByUsername(usernamePasswordToken.getUsername());
	    if(user==null)
	    {
	    	throw new UnknownAccountException("账户不存在");
	    }
	    //密码校验password= password+salt
	    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, usernamePasswordToken.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName());
		return info;
	}

}
