package com.careerlog.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import javax.annotation.Resource;

import com.careerlog.service.UserService;
import com.careerlog.entity.User;
@Controller
@RequestMapping(value="/signIn")
public class signinController {

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String signinHandler(@ModelAttribute("User") User user, BindingResult result,SessionStatus status, ModelMap model){
		userService.insertUser(user);
		model.addAttribute("User", user);
		return "loginSuccess";
	}
}
