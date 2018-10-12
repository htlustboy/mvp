package com.mvp.config;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mvp.util.BaseUtil;

/**
 * shiro验证
 * @author ht
 *
 */
@Configuration
public class MyShiroConfig {
	
	private static final Logger logger = Logger.getLogger(MyShiroConfig.class);
	
	/**
	 * 加载ehCache缓存
	 * @return
	 */
	@Bean
	public EhCacheManager getEhCacheManager(){
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		logger.info("加载缓存......");
		return ehCacheManager;
	}
	
	/**
	 * shiro关键配置，配置shiroFilter
	 * @param manager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager manager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		//配置最基本的url访问路径
		bean.setLoginUrl("/index/index"); //登陆界面
		bean.setUnauthorizedUrl("/unauthor"); //没有权限
		bean.setSuccessUrl("/success"); //成功页面
		
		//设置url访问过滤器
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/index/**", "anon"); //无需权限
		filterChainDefinitionMap.put("/resource/**", "anon"); //静态资源
		filterChainDefinitionMap.put("/*/**", "authc"); //需要权限
		filterChainDefinitionMap.put("/manager/**", "authc[admin]"); //需要管理员权限
		logger.info("加载shiro......");
		return bean;
	}
	
	/**
	 * 配置自定义的权限登陆管理器
	 * @param credentialsMatcher
	 * @return
	 */
	@Bean
	public JdbcRealm jdbcRealm(@Qualifier("credentialsMatcher")CredentialsMatcher credentialsMatcher){
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setCredentialsMatcher(credentialsMatcher);
		jdbcRealm.setCacheManager(getEhCacheManager());
		logger.info("加载shiro权限登陆管理......");
		return jdbcRealm;
	}
	
	/**
	 * 配置事务安全管理
	 * @param jdbcRealm
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(JdbcRealm jdbcRealm){
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(jdbcRealm);
		logger.info("加载事务安全管理......");
		return manager;
	}
	
	/**
	 * 配置自定义密码比较
	 * @return
	 */
	@Bean
	public CredentialsMatcher credentialsMatcher(){
		return new CredentialsMatcher();
	}
	
	/**
	 * 配置生命周期进程
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor leBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}
	
	private class CredentialsMatcher extends SimpleCredentialsMatcher{
		@Override
		public boolean doCredentialsMatch(AuthenticationToken token,AuthenticationInfo info) {
			UsernamePasswordToken utoken = (UsernamePasswordToken) token;
			//获取用户输入密码，采用MD5方式加密
			String inPassword = BaseUtil.password2Hex(utoken.getUsername(),utoken.getPassword().toString());
			//获取数据库密码
			String dbPassword = (String) info.getCredentials();
			return this.equals(inPassword, dbPassword);
		}
	}
}
