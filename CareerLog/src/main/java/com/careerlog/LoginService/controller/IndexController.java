package com.careerlog.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.careerlog.common.GenericController;
import com.careerlog.entity.loginCommand;
import com.careerlog.entity.User;
import com.careerlog.service.FriendService;
import com.careerlog.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController extends GenericController{
	@Resource(name="userService")
	UserService userService;
	@Resource(name="friendService")
	FriendService friendService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String welcome(ModelMap model){
		model.addAttribute("User",new User());
		model.addAttribute("loginCommand",new loginCommand());
		return "/index";
	}
	
	@RequestMapping(value="/{userName}",method=RequestMethod.GET)
	public String welcomeName(@PathVariable("userName") String userName, ModelMap model){
		//User fetchedUser = userService.fetchUserByUserName(userName);
		//int friendsCount = friendService.friendsCount(fetchedUser.getUserId());
		//model.addAttribute("friendsCount",friendsCount);
		//model.addAttribute("fetchedUser",fetchedUser);
		//redirectAttrs.addAttribute("fetchUserName", userName);
		return "forward:/MyPage/"+userName;
	}
}
