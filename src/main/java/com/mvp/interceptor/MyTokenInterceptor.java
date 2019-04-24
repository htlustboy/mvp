package com.mvp.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvp.annotcaion.FormModel;

/**
 * 防止表单重复提交
 * @author ht
 *
 */
public class MyTokenInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Method method = handlerMethod.getMethod();
		FormModel formModel = method.getAnnotation(FormModel.class);
		if(null != formModel){
			
			boolean isNeedSave = formModel.save();
			if(isNeedSave){
				//生成token令牌
				String token = UUID.randomUUID().toString().replace("-", "");
				request.getSession().setAttribute("token", token);
			}
			
			boolean isRepeat = isRepeat(request);
			if(isRepeat){
				request.getSession().removeAttribute("token");
				response.sendRedirect("/index/index.ftl");
			}
		}
		return true;
	}
	
	/**
	 * 是否重复提交
	 * @param request
	 * @return
	 */
	private boolean isRepeat(HttpServletRequest request){
		String clientToken = request.getParameter("token");
		String sessionToken = (String) request.getSession().getAttribute("token");
		if(StringUtils.isBlank(clientToken)){
			return false;
		}
		if(StringUtils.isBlank(sessionToken)){
			return false;
		}
		if(!clientToken.equals(sessionToken)){
			return true;
		}
		return false;
	}
}
