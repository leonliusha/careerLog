package com.careerlog.controller.RelationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import javax.annotation.Resource;
import java.util.List;

import com.careerlog.entity.User;
import com.careerlog.service.UserService;
@Controller
@RequestMapping(value="/findPeople")
public class FriendController {
	
	@Resource(name="userService")
	UserService userService;
	@RequestMapping(method=RequestMethod.POST)
	public String FindPeople(@RequestParam(value="searchingPeopleName") String searchingPeopleName,
							 ModelMap model){
		List<User> queriedUsers = null;		
		if(searchingPeopleName != null && !searchingPeopleName.equals("")){
			queriedUsers = userService.queryUserByName(searchingPeopleName);
		}
		if(queriedUsers != null){
			System.out.println("queriedUsers has "+queriedUsers.size() + " results");
			model.addAttribute("userList",queriedUsers);
			return "peopleSearchingPage";
		}
		else
			return "UserHomePage";
	}
	
}
