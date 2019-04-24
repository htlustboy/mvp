package com.mvp.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 路径参数设置
 * @author ht
 *
 */
public class MyBasePathInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//添加路径变量
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String basePath = scheme + "://" + serverName + ":" + port + "/";
		modelAndView.addObject("base", "");
	}
	
	
}
