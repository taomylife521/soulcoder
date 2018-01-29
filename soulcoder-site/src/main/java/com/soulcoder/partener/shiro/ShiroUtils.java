package com.soulcoder.partener.shiro;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.soulcoder.pojo.SysUser;

/**
 * @author Aministrator
 *
 */

/**
 * The type Shiro utils.
 *
 * @author Aministrator
 */
public class ShiroUtils {


    /**
     * hash算法名称
     */
    public  final static String hashAlgorithmName="SHA-256";

    /**
     * hash迭代轮数
     */
    public  final static int hashIterations=16;

    /**
     * Sha 256 string.
     *
     * @param password the password
     * @param salt     the salt
     * @return the string
     */
    public static String sha256(String password,String salt){
	return new SimpleHash(hashAlgorithmName, password,salt.getBytes(),hashIterations).toString();
}


    /**
     * getSession
     *
     * @return session
     */
    public static Session getSession(){
	return SecurityUtils.getSubject().getSession();
}

    /**
     * getsubject
     *
     * @return subject
     */
    public static Subject getSubject(){
	return SecurityUtils.getSubject();
}


    /**
     * 获取用户实体
     *
     * @return sys user
     */
    public static SysUser getUserEntity(){
	return (SysUser)SecurityUtils.getSubject().getPrincipal();
}

    /**
     *是否登录
     *
     * @return boolean
     */
    public static boolean isLogin(){
	return SecurityUtils.getSubject().getPrincipal()!=null;
}

    /**
     * 登出
     */
    public static void logout(){
	SecurityUtils.getSubject().logout();
}

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    public static void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            //System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }



}
