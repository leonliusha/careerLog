package com.careerlog.controller;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.careerlog.entity.User;
import com.careerlog.service.UserService;

@Controller
public class testController {
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value="/doTest")
	public String doTest(ModelMap model){
		User user = new User();
		user.setUserId(4);
		user.setUserName("MiuMiu");
		user.setEmail("MiuMiu@hotmail.com");
		user.setPassword("20120212");
		user.setFirstName("MiuMiu");
		user.setLastName("Liu");
		userService.insertUser(user);
		
		List<User> users1;
		List<User> users2;
		users1 = userService.queryUserById(4);
		users2 = userService.queryUserByName("kellyjiaojiao");
		model.addAttribute("message","Mybatis test is success.");
		return "index";
		
	}

}
