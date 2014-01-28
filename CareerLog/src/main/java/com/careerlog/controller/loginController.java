package com.careerlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import com.careerlog.entity.loginCommand;
import com.careerlog.service.UserService;
import com.careerlog.entity.User;
import com.careerlog.service.FriendService;
@Controller
@RequestMapping(value="login")
public class loginController {
	@Resource(name="userService")
	UserService userService;
	@Resource(name="friendService")
	FriendService friendService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String doLogin(ModelMap model){
		model.addAttribute("User",new User());
		model.addAttribute("loginCommand",new loginCommand());
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doLogin(@ModelAttribute("loginCommand") loginCommand loginCommand, 
								HttpSession session, ModelMap model, HttpServletRequest request,RedirectAttributes redirectAttrs){
		//clear the command object from session 
		//satus.setComplete();
		User user = userService.queryUserByNameAndPassword(loginCommand);
		if(user==null){
			model.addAttribute("User",new User());
			model.addAttribute("loginCommand",new loginCommand());
			return "login";
		}
		else{
			//List<User> friends = userService.queryFriendById(user.getUserId());
			//int friendsCount = friendService.friendsCount(user.getUserId());
			session.setAttribute("user", user);
			//model.addAttribute("userName", user.getUserName());
			//model.addAttribute("friendsCount",friendsCount);
			//request.setAttribute("userId", user.getUserId());
			
			//after login should add forward request to HomePage.
			//in HomePage servlet, do the other services.
			//for example, get friends count, get message count etc.
			//redirectAttrs.addAttribute("userId", user.getUserId());
			return "redirect:/MyPage";
			//return "redirect:/index";
		}
	}
	
}
