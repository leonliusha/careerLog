package com.careerlog.SigninService.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.careerlog.ProfileService.service.UserService;
import com.careerlog.common.GenericController;
import com.careerlog.ProfileService.entity.User;
@Controller
@RequestMapping(value="/signIn")
public class signinController extends GenericController{

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String signinHandler(@ModelAttribute("User") User user, BindingResult result,SessionStatus status, ModelMap model){
		userService.insertUser(user);
		setCurrentUser(user);
		model.addAttribute("UserId", user.getUserId());
		return "MyPage";
	}
}
