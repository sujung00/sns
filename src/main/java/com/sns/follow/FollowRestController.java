package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

@RestController
public class FollowRestController {

	@Autowired
	private FollowBO followBO; 
	
	/**
	 * follow API
	 * @param userId
	 * @param followId
	 * @param session
	 * @return
	 */
	@RequestMapping("/follow/{followId}")
	public Map<String, Object> follow(
			@PathVariable int followId,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null) {
			result.put("code", 300);
			result.put("errorMessage", "로그인 후 가능합니다");
		}
		
		// db insert
		int rowCount = followBO.followToggle(userId, followId);
		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "팔로우 실패");
		}
		
		return result;
	}
}
