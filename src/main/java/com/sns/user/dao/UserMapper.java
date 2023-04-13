package com.sns.user.dao;

import org.apache.ibatis.annotations.Param;

import com.sns.user.model.User;

public interface UserMapper {

	public User selectUserByLoginId(String loginId);
	
	public int insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("email") String email);
}
