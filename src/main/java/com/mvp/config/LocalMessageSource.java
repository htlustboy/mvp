package com.mvp.config;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalMessageSource {
	
	@Resource
	private MessageSource messageSource;
	
	 /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @return MESSAGE
     */
    public String getMessage(String code) {
        return this.getMessage(code, new Object[] {});
    }

    /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @param defaultMessage 默认MESSAGE
     * @return MESSAGE
     */
    public String getMessage(String code, String defaultMessage) {
        return this.getMessage(code, null, defaultMessage);
    }

    /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @param defaultMessage 默认MESSAGE
     * @param locale 区域信息
     * @return MESSAGE
     */
    public String getMessage(String code, String defaultMessage, Locale locale) {
        return this.getMessage(code, null, defaultMessage, locale);
    }

    /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @param locale 区域信息
     * @return MESSAGE
     */
    public String getMessage(String code, Locale locale) {
        return this.getMessage(code, null, "", locale);
    }

    /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @param args  参数
     * @return MESSAGE
     */
    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, "");
    }

    /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @param args  参数
     * @param locale 区域信息
     * @return MESSAGE
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        return this.getMessage(code, args, "", locale);
    }

    /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @param args  参数
     * @param defaultMessage 默认消息
     * @return MESSAGE
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        // 这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(code, args, defaultMessage, locale);
    }

    /**
     * 根据KEY取得MESSAGE
     * 
     * @param code 对应messages配置的key
     * @param args  参数
     * @param defaultMessage 默认消息
     * @param locale 区域信息
     * @return MESSAGE
     */
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
