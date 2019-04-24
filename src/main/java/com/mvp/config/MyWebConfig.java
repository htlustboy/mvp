package com.mvp.config;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mvp.emun.EmailInterface;
import com.mvp.interceptor.MyTokenInterceptor;

@Configuration
@MapperScan(basePackages="com.mvp.dao")
public class MyWebConfig extends WebMvcConfigurerAdapter{
	
	private static final Logger logger = Logger.getLogger(MyWebConfig.class);
	
	@Resource
	private Environment environment;
	
	
	/**
	 * 创建数据源
	 * @return
	 */
	@Bean
	public DataSource getDataSource(){
		Properties properties = new Properties();
		properties.put("driverClassName", environment.getProperty("jdbc.driverClassName"));
		properties.put("url", environment.getProperty("jdbc.url"));
		properties.put("username", environment.getProperty("jdbc.username"));
		properties.put("password", environment.getProperty("jdbc.password"));
		DataSource dataSource = null;
		try {
			dataSource = DruidDataSourceFactory.createDataSource(properties);
			logger.info("数据源加载完毕......");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return dataSource;
	}
	
	/**
	 * 创建sqlSessionFactory
	 * @param dataSource
	 * @return
	 */
	@Bean
	public SqlSessionFactory createSqlSessionFactory(DataSource dataSource){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(environment.getProperty("mybatis.config")));
		sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));
		try {
			sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapperLocations")));
			logger.info("sqlSessionFactory创建成功......");
			return sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sqlSessionFactory创建失败:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 配置国际化
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver(){
		CookieLocaleResolver slr = new CookieLocaleResolver();
//		slr.setCookieName("i18n_language");
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return slr;
	}
	
   // http://guanxi.iteye.com/blog/2304607
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    /**
     * 邮件配置
     * @return
     */
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl(){
    	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    	javaMailSender.setHost(EmailInterface.Email_Host);
    	javaMailSender.setUsername(EmailInterface.Email_Name);
    	javaMailSender.setPassword(EmailInterface.Email_Password);
    	javaMailSender.setProtocol(EmailInterface.Email_Protocol);
    	javaMailSender.setDefaultEncoding("UTF-8");
    	javaMailSender.getJavaMailProperties().put("mail.smtp.auth", true);
    	javaMailSender.getJavaMailProperties().put("mail.smtp.timeout", 25000);
    	return javaMailSender;
    }
    
    @Bean 
    public SimpleMailMessage getSimpleMailMessage(){
    	return new SimpleMailMessage();
    }
    
	/* 
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry){
//		registry.addInterceptor(new MyBasePathInterceptor()).addPathPatterns("/**"); //路径拦截器
		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**"); //国际化拦截器
		registry.addInterceptor(new MyTokenInterceptor()).addPathPatterns("/**"); //路径拦截器
	}
}
