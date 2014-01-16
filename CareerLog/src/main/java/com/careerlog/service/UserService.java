package com.careerlog.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.careerlog.entity.User;
import com.careerlog.orm.UserDao;
import com.careerlog.entity.loginCommand;

@Service(value="userService")
@Transactional(rollbackFor=Exception.class)
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@SuppressWarnings("rawtypes")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<User> queryUserByName(String userName){
		return userDao.queryUserByName("UserMapper.queryUserByName", userName);
	}
	
	public User queryUserByNameAndPassword(loginCommand login){
		return userDao.queryUserByNameAndPassword("UserMapper.queryUserByNameAndPassword", login);
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
	
	public List<User> queryFriendById(int id){
		return userDao.queryFriendById("UserMapper.queryFriendById", id);
	}

}
