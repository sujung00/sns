package com.sns.profile;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.user.bo.UserBO;

@RequestMapping("/profile")
@RestController
public class ProfileRestController {
	
	@Autowired
	private UserBO userBO;

	@PutMapping("/profile_edit")
	public Map<String, Object> profileEdit(
			@RequestParam("userId") int userId,
			@RequestParam("name") String name,
			@RequestParam("loginId") String loginId,
			@RequestParam("email") String email,
			@RequestParam(value="file", required=false) MultipartFile file,
			@RequestParam(value="fileName", required=false) String fileName){
		Map<String, Object> result = new HashMap<>();

		int rowCount = 0;
		// TODO db update
		if(file == null) {
			if(fileName.equals("기본 이미지")) {
				rowCount = userBO.updateUserById(userId, name, loginId, email, null);
			}else {
				rowCount = userBO.updateUserByIdNotImage(userId, name, loginId, email);
			}
		} else {
			rowCount = userBO.updateUserById(userId, name, loginId, email, file);
		}
		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "프로필 수정에 실패했습니다.");
		}
		
		return result;
	}
}
