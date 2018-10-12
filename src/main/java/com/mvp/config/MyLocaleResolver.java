package com.mvp.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

/**
 * 国际化配置
 * @author ht
 *
 */
public class MyLocaleResolver implements LocaleResolver{
	
	private static final String I18N_LANGUAGE = "i18n_language";
	private static final String I18N_LANGUAGE_SESSION = "i18n_language_session";
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String i18n_language = request.getParameter(I18N_LANGUAGE);
		Locale locale = Locale.getDefault();
		if(StringUtils.isEmpty(i18n_language)){
			//如果参数为空，则使用默认国际化参数
			HttpSession session = request.getSession();
			Locale localInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);
			if(localInSession != null){
				locale = localInSession;
			}
		}else{
			//参数不为空
			String[] language = i18n_language.split("_");
			locale = new Locale(language[0], language[1]);
			//保存到session中
			HttpSession session = request.getSession();
			session.setAttribute(I18N_LANGUAGE_SESSION, locale);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		
		locale = resolveLocale(request);
	}

}
