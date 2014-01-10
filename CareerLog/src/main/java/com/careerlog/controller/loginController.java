package com.careerlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.*;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import com.careerlog.entity.loginCommand;
@Controller

@RequestMapping(value="/login")
public class loginController {
	
	@RequestMapping(method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("loginCommand") loginCommand loginCommand, 
								BindingResult result, SessionStatus status, ModelMap model){
		//clear the command object from session 
		//satus.setComplete();
		model.addAttribute("userName",loginCommand.getUserName());
		model.addAttribute("password",loginCommand.getPassword());
		return "loginSuccess";
	}
	
	
	
}
