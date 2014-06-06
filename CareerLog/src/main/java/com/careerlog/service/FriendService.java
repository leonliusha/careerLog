package com.careerlog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.careerlog.orm.FriendDao;
import com.careerlog.entity.Friend;
import com.careerlog.entity.User;

@Service(value="friendService")
@Transactional(rollbackFor=Exception.class)
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	
	public void insertFriend(Friend friend){
		friendDao.insertFriend("FriendMapper.insertFriend", friend);
	}
	
	public Integer friendsCount(int userId){
		return friendDao.friendsCount("FriendMapper.friendsCount", userId);
	}
	
	public List<User> queryFriendsByName(String userName){
		return friendDao.queryFriendsByName("FriendMapper.queryFriendsByName", userName);
	}
}
