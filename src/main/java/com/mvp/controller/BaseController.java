package com.mvp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ControllerAdvice
public class BaseController implements EnvironmentAware{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Environment environment;
	
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.METHOD})
	public @interface FormData{
		boolean save() default false;
		boolean remove() default false;
	}
	
	   /** 取得HttpServletRequest */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /** 取得HttpServletResponse */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
    protected static class R{
    	public static final String LAYOUT_PAGE = "layout";
    	public static final String REDIRECT_PAGE = "redirect";
    	
    	public static String view(String viewPath){
    		return viewPath;
    	}
    	
	    public static String view(String viewPath, boolean isRediredt) {
           return String.format("%s%s", isRediredt ? "redirect:" : "", viewPath);
        }
    }
    
    //=======================================================================================
    
    //文件输出
    public Object readFile(String filePath){
    	StringBuilder sb = new StringBuilder("");
    	  File file = new File(filePath);
          BufferedReader reader = null;
          try {
              reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
              String tempString = null;
              while ((tempString = reader.readLine()) != null) {
                  sb.append(tempString+"<br>");
              }
              reader.close();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              if (reader != null) {
                  try {
                      reader.close();
                  } catch (IOException e1) {
                	  logger.warn(e1.getMessage());
                  }
              }
          }
          return sb.toString();
    }
    
    //=======================================================================================
    
}
