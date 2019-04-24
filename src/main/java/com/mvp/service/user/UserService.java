package com.mvp.service.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.mvp.common.TaskStatus;
import com.mvp.dao.UserMapper;
import com.mvp.model.User;

@Service
public class UserService {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Resource
	UserMapper userMapper;

	/**
	 * 根据用户名查找用户（登陆认证）
	 * @param username
	 * @return
	 */
	public User queryUserByUserName(String username) {
		return userMapper.findUserByUserName(username);
	}

	/**
	 * 登录
	 * @param user
	 */
	public TaskStatus doLogin(User user) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				logger.warn(uae.getMessage());
				return TaskStatus.WARNING("未找到该用户!");
			}catch (IncorrectCredentialsException ice) {
				logger.warn(ice.getMessage());
				return TaskStatus.WARNING("密码错误!");
			} catch (LockedAccountException lae) {
				logger.warn(lae.getMessage());
				return TaskStatus.WARNING("账号被在锁定!");
			} 
		}
		return TaskStatus.SUCCESS();		
	}
	
}
