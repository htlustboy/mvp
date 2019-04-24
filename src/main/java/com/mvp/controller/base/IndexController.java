package com.mvp.controller.base;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.annotcaion.FormModel;
import com.mvp.common.TaskStatus;
import com.mvp.config.LocalMessageSource;
import com.mvp.controller.BaseController;
import com.mvp.model.User;
import com.mvp.service.email.EmailService;
import com.mvp.service.user.UserService;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	@Resource
	private UserService userService;
	
	@Resource
	private EmailService emailService;
	
	@Resource
	LocalMessageSource localMessageSource;
	
	//程序入口
	@RequestMapping("index")
	public String index(Model model){
		return "index/index";
	}
	
	//登陆
	@FormModel
	@RequestMapping("/login")
	public String doLogin(Model model,@ModelAttribute("user")User user){
		TaskStatus status = userService.doLogin(user);
		if(!status.isSuccess()){
			model.addAttribute("message", status.getMessage());
		}
		return "index/index";
	}
	
	//注册
	@RequestMapping("/register")
	public String register(){
		return "register/register";
	}
	
}
