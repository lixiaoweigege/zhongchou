package com.dushan.zhongchou.mvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestHandler {
	
	@ResponseBody
	@RequestMapping("/ajax/error.json")
	public String error() {
		
		System.out.println(10 / 0);
		
		return "xxx";
	}
	
	@RequestMapping("/test.html")
	public String test(Model model) {
		
		System.out.println(6666);
		
		model.addAttribute("hello", "hellovalue");
		
		return "target";
	}

}
