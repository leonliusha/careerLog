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
		user.setUserId(2);
		user.setCode("kellyjiaojiao");
		user.setEmail("jiaojiaokelly@hotmail.com");
		user.setPassword("19820812");
		user.setState("Shanghai");
		userService.insertUser(user);
		
		List<User> users1;
		List<User> users2;
		users1 = userService.queryUserById(2);
		users2 = userService.queryUserByName("kellyjiaojiao");
		model.addAttribute("message","Mybatis test is success.");
		return "index";
		
	}

}
