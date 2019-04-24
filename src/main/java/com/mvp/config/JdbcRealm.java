package com.mvp.config;



import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.mvp.model.User;

public class JdbcRealm extends AuthorizingRealm{
	
	private static final Logger logger = Logger.getLogger(JdbcRealm.class);
	
	@Resource
	private com.mvp.service.user.UserService userService;
	
	//登陆认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		String username = uToken.getUsername();
		User user = userService.queryUserByUserName(username);
		if(null == user){
			logger.warn("未找到该用户！");
			throw new UnknownAccountException("未找到该用户！");
		}
		Object credentials = user.getPassword();
		//盐值
		ByteSource salt = ByteSource.Util.bytes(username);
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, credentials, salt, getName());
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("user", user);
		return info;
	}
		
	//授权认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
