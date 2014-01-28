package com.careerlog.service;

import org.springframework.stereotype.Service;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.List;
import java.util.Map;
import com.careerlog.entity.Message;

@Service(value="messageService")
public class MessageService extends SqlSessionDaoSupport{
	public int queryMessageCountByUserName(Map<String,String> queryInfo){
		return getSqlSession().selectOne("MessageMapper.queryMessageCountByUserName",queryInfo);
	}
	
	public void insertMessage(Message message){
		getSqlSession().insert("MessageMapper.insertMessage", message);
	}
}
