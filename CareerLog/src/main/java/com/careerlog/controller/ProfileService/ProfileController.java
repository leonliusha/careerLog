package com.careerlog.controller.ProfileService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.careerlog.entity.loginCommand;
import com.careerlog.entity.User;
import com.careerlog.service.FriendService;
import com.careerlog.service.UserService;

@Controller
@RequestMapping(value="/MyPage")
public class ProfileController {
	@Resource(name="userService")
	UserService userService;
	@Resource(name="friendService")
	FriendService friendService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String LoginHomePageController(ModelMap model, HttpSession session){
		User user = (User)session.getAttribute("user");
		//int userId = ((Integer)request.getAttribute("userId")).intValue();
		int friendsCount = friendService.friendsCount(user.getUserId());
		model.addAttribute("fetchedUser", user);
		model.addAttribute("friendsCount",friendsCount);
		return "MyPage";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String HomePageController(HttpServletRequest request, ModelMap model, HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user==null)
			return "login";
		else{
			//int userId = Integer.parseInt((String)request.getAttribute("userId"));
			int friendsCount = friendService.friendsCount(user.getUserId());
			model.addAttribute("fetchedUser", user);
			model.addAttribute("friendsCount",friendsCount);
			return "MyPage";			
		}
	}
	
	@RequestMapping(value="/{fetchUserName}",method=RequestMethod.GET)
	public String HomePageController(@PathVariable("fetchUserName") String fetchUserName, ModelMap model){
		User fetchedUser = userService.fetchUserByUserName(fetchUserName);
		int friendsCount = friendService.friendsCount(fetchedUser.getUserId());
		model.addAttribute("friendsCount",friendsCount);
		model.addAttribute("fetchedUser",fetchedUser);
		return "MyPage";
	}

}
