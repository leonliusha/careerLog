package com.careerlog.controller.RelationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

import com.careerlog.entity.User;
import com.careerlog.entity.Friend;
import com.careerlog.service.UserService;
import com.careerlog.service.FriendService;
@Controller
public class FriendController {
	
	@Resource(name="userService")
	UserService userService;
	@Resource(name="friendService")
	FriendService friendService;
	
	@RequestMapping(value="/findPeople",method=RequestMethod.POST)
	public String FindPeople(@RequestParam(value="searchingPeopleName") String searchingPeopleName,
							 ModelMap model){
		List<User> searchedUsers = null;		
		if(searchingPeopleName != null && !searchingPeopleName.equals("")){
			searchedUsers = userService.queryUserByName(searchingPeopleName);
		}
		if(searchedUsers != null){
			System.out.println("searchedUsers has "+searchedUsers.size() + " results");
			model.addAttribute("searchedUserList",searchedUsers);
			return "peopleSearchingPage";
		}	
		else
			return "MyPage";
	}
	
	@RequestMapping(value="/findPeople/addUserToFriends",method=RequestMethod.POST)
	public @ResponseBody String addUserToFriends(@RequestParam(value="friendId") String friendId,ModelMap model,HttpSession session){
		System.out.println("friendId is"+friendId);
		User user = (User)session.getAttribute("user");
		String returnText;
		if(user==null){
			return "login";
		}
		else{
			int userId = user.getUserId();
			friendService.insertFriend(new Friend(userId,Integer.parseInt(friendId)));
			System.out.println("user id:"+userId+"friend id:"+friendId);
			returnText = "<spring:message code='common.friends.add.success' />";
			return returnText;
		}
		
	}
	
	@RequestMapping(value="/friends/{userName}")
	public String listFriends(@PathVariable("userName") String userName, ModelMap model){
		if(userName!=null && !userName.equals(""))//should be changed
		{
			List<User> friends = friendService.queryFriendsByName(userName);
			model.addAttribute("friendsList", friends);
			return "FriendsPage";
		}
		else
			return "errorPage";
	}
	
}
