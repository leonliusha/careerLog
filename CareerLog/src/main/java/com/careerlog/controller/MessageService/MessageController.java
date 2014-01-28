package com.careerlog.controller.MessageService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import com.careerlog.entity.Message;
import com.careerlog.entity.User;
import com.careerlog.service.MessageService;
import com.careerlog.util.MessageType;

@Controller
@RequestMapping(value="/message")
public class MessageController {
	@Resource(name="messageService")
	MessageService messageService;
	
	@RequestMapping(value="/newlog",method=RequestMethod.GET)
	public String writeNewLog(ModelMap model){
		model.addAttribute("Message",new Message());	
		return "NewLogPage";
	}
	
	@RequestMapping(value="/newlog",method=RequestMethod.POST)
	public String writeNewLog(@ModelAttribute("Message") Message message, HttpSession session, ModelMap model){
		User user = (User)session.getAttribute("user");
		message.setUserId(user.getUserId());
		message.setUserName(user.getUserName());
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		message.setCreationDate(timeStamp);
		MessageType messageType = new MessageType();
		String messageTypeId =  messageType.getLog();
		message.setMessageTypeId(messageTypeId);
		message.setViewCount(0);
		message.setScore(0);
		message.setCommentCount(0);
		System.out.println(message.toString());
		messageService.insertMessage(message);
		return "redirect:/MyPage";
	}
	
	
}
