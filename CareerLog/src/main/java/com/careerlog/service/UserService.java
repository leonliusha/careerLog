package com.careerlog.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.careerlog.entity.User;
import com.careerlog.orm.UserDao;

@Service(value="userService")
@Transactional(rollbackFor=Exception.class)
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<User> queryUserByName(String name){
		return userDao.queryUserByName("UserMapper.queryUserByName", name);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> queryUserById(int id){
		return userDao.queryUserById("UserMapper.queryUserById", id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertUser(User user){
		userDao.insertUser("UserMapper.insertUser", user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUser(User user){
		userDao.updateUser("UserMapper.updateUser", user);
	}
	

}
