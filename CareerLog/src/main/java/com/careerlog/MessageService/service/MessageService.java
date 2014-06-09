package com.careerlog.MessageService.service;

import org.springframework.stereotype.Service;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

import com.careerlog.MessageService.entity.Message;
import com.careerlog.MessageService.entity.Page;

@Service(value="messageService")
public class MessageService extends SqlSessionDaoSupport{
	
	public List<Message> queryAllMessages(){
		return getSqlSession().selectList("MessageMapper.queryAllMessages");
	}
	
	public Integer queryMessageCountByUserName(Map<String,String> queryInfo){
		return getSqlSession().selectOne("MessageMapper.queryMessageCountByUserName",queryInfo);
	}
	
	public void insertMessage(Message message){
		getSqlSession().insert("MessageMapper.insertMessage", message);
	}
	
	public List<Message> queryMessageByTitle(String title){
		return getSqlSession().selectList("MessageMapper.queryMessageByTitle", title);
	}
	
	public List<Message> queryMessageByPage(Page<Message> page){
		return getSqlSession().selectList("MessageMapper.queryMessageByPage",page);
	}
	
	public Message queryMessageById(int messageId){
		return getSqlSession().selectOne("MessageMapper.queryMessageById", messageId);
	}
}
