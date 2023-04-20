package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.user.dao.UserMapper;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private FileManagerService fileManager;

	public User getUserByLoginId(String loginId) {
		return userMapper.selectUserByLoginId(loginId);
	}
	
	public int addUser(String loginId, String password, String name, String email) {
		return userMapper.insertUser(loginId, password, name, email);
	}
	
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userMapper.selectUserByLoginIdPassword(loginId, password);
	}
	
	public User getUserByUserId(int userId) {
		return userMapper.selectUserByUserId(userId);
	}
	
	public int updateUserById(int userId, String name, String loginId, String email, MultipartFile file) {
		
		String imagePath = null;
		if(file != null) {
			// 서버에 이미지 업로드 후 imagPath 받아옴
			imagePath = fileManager.saveFile(loginId, file);
		}
		
		return userMapper.updateUserById(userId, name, loginId, email, imagePath);
	}
	
	public int updateUserByIdNotImage(int userId, String name, String loginId, String email) {
		return userMapper.updateUserByIdNotImage(userId, name, loginId, email);
	}
}
