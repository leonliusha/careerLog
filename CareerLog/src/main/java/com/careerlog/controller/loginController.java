package com.careerlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.*;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import javax.annotation.Resource;
import java.util.List;
import com.careerlog.entity.loginCommand;
import com.careerlog.service.UserService;
import com.careerlog.entity.User;

@Controller

@RequestMapping(value="/login")
public class loginController {
	@Resource(name="userService")
	UserService userService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("loginCommand") loginCommand loginCommand, 
								BindingResult result, SessionStatus status, ModelMap model){
		//clear the command object from session 
		//satus.setComplete();
		User user = userService.queryUserByNameAndPassword(loginCommand);
		if(user==null){
			user = new User();
			loginCommand = new loginCommand();
			model.addAttribute("User",user);
			model.addAttribute("loginCommand",loginCommand);
			return "index";
		}
		else{
			List<User> friends = userService.queryFriendById(user.getUserId());
			model.addAttribute("userName",user.getUserName());
			model.addAttribute("userId",user.getUserId());
			model.addAttribute("friendNumber",friends.size());
			return "UserHomePage";
		}
	}
	
}
