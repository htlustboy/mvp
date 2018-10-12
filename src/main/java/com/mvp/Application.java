package com.mvp;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	private static final Logger logger = Logger.getLogger(Application.class);
	
	/**
	 * 程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		
		logger.info("================================start:");
		
		SpringApplication.run(Application.class, args);
		
		logger.info("================================end");
	}
}
