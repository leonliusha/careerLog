package com.careerlog.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.careerlog.entity.loginCommand;
import com.careerlog.entity.User;

@Controller
@RequestMapping("/")
public class BaseController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String welcome(ModelMap model){
		loginCommand login = new loginCommand();
		User user = new User();
		model.addAttribute("loginCommand",login);
		model.addAttribute("User",user);
		return "index";
	}
	
	@RequestMapping(value="/index/{name}",method=RequestMethod.GET)
	public String welcomeName(@PathVariable("name") String name, ModelMap model){
		model.addAttribute("message","Maven Web Project + Spring 3MVC +myBatis- "+name);
		return "index";
	}
	
	
}
