package com.mvp.service.email;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private static final Logger logger = Logger.getLogger(EmailService.class);
	
	@Resource
	private JavaMailSenderImpl javaMailSender;
	
	@Resource
	private SimpleMailMessage simpleMessage;
	
	public void sendEmail(){
		simpleMessage.setFrom("hutao1187@163.com");
		simpleMessage.setTo("838533527@qq.com");
		simpleMessage.setSubject("Spring测试发送邮件");
		simpleMessage.setText("邮件正文....");
		simpleMessage.setSentDate(new Date());
		javaMailSender.send(simpleMessage);
		System.out.println("发送成功");
	}
	
	/**
	 * @param from 发送人
	 * @param to 接收人
	 * @param subject 标题
	 * @param text 正文
	 */
	public void sendEmail(String from, String to, String subject, String text){
		simpleMessage.setFrom(from);
		simpleMessage.setTo(to);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(text);
		simpleMessage.setSentDate(new Date());
		javaMailSender.send(simpleMessage);
		logger.info("email发送成功！");
	}
}
