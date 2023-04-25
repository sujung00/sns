package com.sns.user.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.user.dao.UserMapper;
import com.sns.user.model.User;

@Service
public class UserBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	public int updateUserById(int userId, String loginId,
			String name, String email, MultipartFile file, String fileName) {
		
		// 기존 사용자을 가져온다(이미지가 교체될 때 기존 이미지 제거를 위해서)
		User user = getUserByUserId(userId);
		if(user == null) {
			logger.warn("[update user] user is null. userId:{}", userId);
		}
		
		// 업로드한 이미지가 있으면 서버 업로드 => imagePath 받아옴 => 업로드 성공하면 기존 이미지 제거
		String imagePath = null;
		if(file != null) {
			// 업로드
			imagePath = fileManager.saveFile(loginId, file);
			
			// 성공 여부 체크 후 기존 이미지 제거
			// -- imagePath가 null이 아닐 때(성공) 그리고 기존 이미지가 있을 때 => 기존 이미지 삭제
			if(imagePath != null && user.getProfileImagePath() != null) {
				// 이미지 제거
				fileManager.deleteFile(user.getProfileImagePath());
			}
		}
		
		if(fileName.equals("기본 이미지")) {
			// 이미지 제거
			fileManager.deleteFile(user.getProfileImagePath());
			imagePath = "";
		}
		
		return userMapper.updateUserById(userId, loginId, name, email, imagePath);
	}
}
