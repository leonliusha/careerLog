package com.careerlog.orm;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.careerlog.entity.User;
import java.io.Serializable;
import java.util.List;

public class UserDao extends SqlSessionDaoSupport{
	
	public void insertUser(String key, Object object){
		getSqlSession().insert(key, object);
	}
	
	public void updateUser(String key, Object object){
		getSqlSession().delete(key,object);
	}
	
	public List<User> queryUserByName(String key, String name){
		return getSqlSession().selectList(key,name);
	}
	
	public List<User> queryUserById(String key, Serializable id){
		return getSqlSession().selectList(key, id);
	}
	
	public User queryUserByNameAndPassword(String key, Object object){
		return getSqlSession().selectOne(key, object);
	}
	
	public List<User> queryFriendById(String key,Serializable id){
		return getSqlSession().selectList(key, id);
	}
	

}
