package com.sns.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.sns.user.model.User;

public interface UserMapper {

	public User selectUserByLoginId(String loginId);
	
	public int insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("email") String email);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
	
	public User selectUserByUserId(int userId);
	
	public int updateUserById(
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("name") String name,
			@Param("email") String email,
			@Param("profileImagePath") String profileImagePath);
	
	public List<User> selectUserListByLoginId(String loginId);
}
