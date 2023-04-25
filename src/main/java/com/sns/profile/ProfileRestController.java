package com.sns.profile;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
			@RequestParam("name") String name,
			@RequestParam("loginId") String loginId,
			@RequestParam("email") String email,
			@RequestParam(value="file", required=false) MultipartFile file,
			@RequestParam("fileName") String fileName,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		// 세션 정보
		int userId = (int)session.getAttribute("userId");
		
		// db update
		int rowCount = userBO.updateUserById(userId, loginId, name, email, file, fileName);
		
		// 응답
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "프로필 편집에 실패했습니다");
		}
		
		return result;
	}
}
