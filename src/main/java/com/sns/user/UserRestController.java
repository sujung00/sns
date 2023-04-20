package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	/**
	 * 아이디 중복확인
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDulplicatedId(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		
		// select
		User user = userBO.getUserByLoginId(loginId);
		
		if(user != null) {			
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 1);
			result.put("result", false);
		}
		
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		
		// password 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		Map<String, Object> result = new HashMap<>();
		
		// db insert
		int rowCount = userBO.addUser(loginId, hashedPassword, name, email);
		
		if(rowCount == 1) {			
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입에 실패했습니다.");
		}
		
		return result;
	}
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session){
		String hashedPassword = EncryptUtils.md5(password);
		
		Map<String, Object> result = new HashMap<>();
		
		//db select
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);
		
		if(user != null) {
			result.put("code", 1);
			result.put("result", "성공");
			
			//세션에 유저 추가
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userLoginId", user.getLoginId());
		} else {
			result.put("code", 500);
			result.put("errorMessage", "존재하지 않는 사용자입니다");
		}
		
		return result;
	}
	
	@PutMapping("/profile_edit")
	public Map<String, Object> profileEdit(
			@RequestParam("userId") int userId,
			@RequestParam("name") String name,
			@RequestParam("loginId") String loginId,
			@RequestParam("email") String email,
			@RequestParam(value="file", required=false) MultipartFile file){
		Map<String, Object> result = new HashMap<>();

		int rowCount = 0;
		// TODO db update
		if(file == null) {
			rowCount = userBO.updateUserByIdNotImage(userId, name, loginId, email);
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
