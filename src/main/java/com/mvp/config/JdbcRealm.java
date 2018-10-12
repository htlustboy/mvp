package com.mvp.config;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.mvp.model.User;

public class JdbcRealm extends AuthorizingRealm{

	//授权认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//登陆认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		String username = uToken.getUsername();
//		User user 
		return null;
	}
	
//	@Resource
//	private UserService userService;
//	
//	@Resource
//	private RoleService roleService;
//	
//	@Resource
//	private PermissionService permissionService;
//	
//	//授权
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(
//			PrincipalCollection principals) {
//		User principal = (User) super.getAvailablePrincipal(principals);
//		if(principal!=null){
//			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//			//获取用户的角色集合
//			Set<String> roles = roleService.getRoleNameByUserId(principal.getId()); 
//			info.setRoles(roles);
//			//获取用户所有的权限
//			java.util.List<Role> roleList = roleService.getRoleListByUserId(principal.getId());
//			for (Role r : roleList) {
//				List<String> permissionNames = permissionService.getPermissionNameByRid(r.getId());
//				info.addStringPermissions(permissionNames);
//			}
//			return info;
//		}
//		return null;
//	}
//	
//	//登录认证
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(
//			AuthenticationToken token) throws AuthenticationException {
//		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//		String username = upToken.getUsername();
//		User user = userService.queryUserByName(username);
//		if(user==null){
//			throw new UnknownAccountException("未找到该用户");
//		}
//		//user不为空，则获取该密码
//		Object credentials = user.getPassword();
//		//盐值
//		ByteSource salt = ByteSource.Util.bytes(username);
//		AuthenticationInfo info = new SimpleAuthenticationInfo(user, credentials, salt,getName());
//		Subject currentUser = SecurityUtils.getSubject();
//		Session session = currentUser.getSession();
//		session.setAttribute("user", user);
//		return info;
//	}

}
