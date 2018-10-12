package com.mvp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.mvp.config.MyLocaleResolver;

/**
 * 国际化参数拦截器
 * @author ht
 *
 */
public class MyLocaleInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = Logger.getLogger(HandlerInterceptorAdapter.class);
	
	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		LocaleResolver localeResolver = new MyLocaleResolver();
		if(null == localeResolver){
			logger.error("国际化加载失败......");
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
		}
		try {
			localeResolver.setLocale(request, response, localeResolver.resolveLocale(request));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		
		return true;
	}
}
