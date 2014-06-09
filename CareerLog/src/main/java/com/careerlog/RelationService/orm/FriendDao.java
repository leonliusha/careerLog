package com.careerlog.RelationService.orm;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.io.Serializable;
import java.util.List;
import com.careerlog.ProfileService.entity.User;


public class FriendDao extends SqlSessionDaoSupport{
	public void insertFriend(String key,Object object){
		getSqlSession().insert(key, object);
	}
	
	public Integer friendsCount(String key, Serializable id){
		return getSqlSession().selectOne(key, id);
	}
	
	public List<User> queryFriendsByName(String key, String userName){
		return getSqlSession().selectList(key, userName);
	}
}

