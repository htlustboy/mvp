package com.mvp.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvp.controller.BaseController;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	@RequestMapping("index")
	public String index(Model model){
		
		return "index/index";
	}
}
