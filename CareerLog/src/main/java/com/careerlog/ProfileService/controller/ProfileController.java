package com.careerlog.ProfileService.controller;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.careerlog.common.GenericController;
import com.careerlog.LoginService.entity.loginCommand;
import com.careerlog.ProfileService.entity.User;
import com.careerlog.RelationService.service.FriendService;
import com.careerlog.MessageService.service.MessageService;
import com.careerlog.ProfileService.service.UserService;
import com.careerlog.util.MessageType;

@Controller
@RequestMapping(value="/MyPage")
public class ProfileController extends GenericController{
	@Resource(name="userService")
	UserService userService;
	@Resource(name="friendService")
	FriendService friendService;
	@Resource(name="messageService")
	MessageService messageService;
	
	@RequestMapping(method=RequestMethod.POST)
	public String LoginHomePageController(ModelMap model){
		User user = null;
		Object object = getCurrentUser();
		if(object == null && !(object instanceof User))
			return "login";
		user = (User)object;
		MessageType messageType= new MessageType();
		//get the count of friends
		int friendsCount = friendService.friendsCount(user.getUserId()).intValue();
		//get the count of logs
		Map<String,String> queryInfo = new HashMap<String, String>();
		queryInfo.put("userName", user.getUserName());
		queryInfo.put("messageTypeId", messageType.getLog());
		int logsCount = messageService.queryMessageCountByUserName(queryInfo).intValue();	
		model.addAttribute("fetchedUser", user);
		model.addAttribute("friendsCount",friendsCount);
		model.addAttribute("logsCount",logsCount);
		return "MyPage";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String HomePageController( ModelMap model){
		User user = null;
		Object object = getCurrentUser();
		if(object == null && !(object instanceof User))
			return "login";
		user = (User)object;
		MessageType messageType= new MessageType();
		if(user==null)
			return "forward:login";
		else{
			//int userId = Integer.parseInt((String)request.getAttribute("userId"));
			int friendsCount = friendService.friendsCount(user.getUserId()).intValue();
			Map<String,String> queryInfo = new HashMap<String, String>();
			queryInfo.put("userName", user.getUserName());
			queryInfo.put("messageTypeId", messageType.getLog());
			int logsCount = messageService.queryMessageCountByUserName(queryInfo).intValue();
			model.addAttribute("fetchedUser", user);
			model.addAttribute("friendsCount",friendsCount);
			model.addAttribute("logsCount",logsCount);
			return "MyPage";			
		}
	}
	
	@RequestMapping(value="/{fetchUserName}",method=RequestMethod.GET)
	public String HomePageController(@PathVariable("fetchUserName") String fetchUserName, ModelMap model){
		User fetchedUser = userService.fetchUserByUserName(fetchUserName);
		int friendsCount = friendService.friendsCount(fetchedUser.getUserId()).intValue();
		MessageType messageType= new MessageType();
		Map<String,String> queryInfo = new HashMap<String, String>();
		queryInfo.put("userName", fetchedUser.getUserName());
		queryInfo.put("messageTypeId",messageType.getLog());
		int logsCount = messageService.queryMessageCountByUserName(queryInfo).intValue();
		model.addAttribute("friendsCount",friendsCount);
		model.addAttribute("fetchedUser",fetchedUser);
		model.addAttribute("logsCount",logsCount);
		return "MyPage";
	}

}
