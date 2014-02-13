package com.careerlog.controller.MessageService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import com.careerlog.entity.Message;
import com.careerlog.entity.Page;
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
		Timestamp timeStamp = new Timestamp(new Date().getTime());
		message.setCreationDate(timeStamp);
		String messageTypeId =  new MessageType().getLog();
		message.setMessageTypeId(messageTypeId);
		message.setViewCount(0);
		message.setScore(0);
		message.setCommentCount(0);
		System.out.println(message.toString());
		messageService.insertMessage(message);
		return "redirect:/MyPage";
	}
	
	@RequestMapping(value="/logs",method=RequestMethod.GET)
	public String getLogsByPage(ModelMap model,HttpSession session){
		Page<Message> page = new Page<Message>();
		User user = (User)session.getAttribute("user");
		page.setParams("userName", user.getUserName());
		page.setParams("messageTypeId", new MessageType().getLog());
		page.setStart(2);
		page.setResults(messageService.queryMessageByPage(page));
		int resultSize = page.getResults().size();
		for(int i=0; i<resultSize;i++){
			String contentIntro = this.getContentIntro(page.getResults().get(i).getText());
			page.getResults().get(i).setText(contentIntro);
		}
		model.addAttribute("page",page);
		return "logsPage";	
	}
	
	@RequestMapping(value="/logs/{paginationNumber}")
	public String getLogsByPagination(@PathVariable("paginationNumber") int paginationNumber, ModelMap model, HttpSession session){
		Page<Message> page = new Page<Message>();
		User user = (User)session.getAttribute("user");
		page.setParams("userName",user.getUserName());
		page.setParams("messageTypeId", new MessageType().getLog());
		page.setStart(paginationNumber);
		page.setResults(messageService.queryMessageByPage(page));
		return "logsPage";
	}
	
	@RequestMapping(value="/logid/{messageId}")
	public String getLogById(@PathVariable("messageId") int messageId, ModelMap model, HttpSession session){
		Message message = messageService.queryMessageById(messageId);
		model.addAttribute("message",message);
		return "logPage";
	}
	
	
	private String getContentIntro(String content){
		if(content==null)
			return "";
		int stringLength=0;
		int count =0;
		StringBuffer sb = new StringBuffer();
		char[] chars = content.toCharArray();
		int index = 0;
		for(index=0;index<chars.length;index++){
			if(chars[index]<255){
				count++;
				if(count==2){
					stringLength++;
					count=0;
				}
			}
			else{
				stringLength++;
			}
			if(stringLength>100){
				 return sb.toString();
			}
			sb.append(chars[index]);
		}
		return sb.toString();
	}
	
}
