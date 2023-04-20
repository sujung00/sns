package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

@RequestMapping("/follow")
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
	@PostMapping("/follow")
	public Map<String, Object> follow(
			@RequestParam("userId") int userId,
			@RequestParam("followId") int followId,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		// db insert
		int rowCount = followBO.addFollow(userId, followId);
		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "팔로우 실패");
		}
		
		return result;
	}
	
	@PostMapping("/unfollow")
	public Map<String, Object> unfollow(
			@RequestParam("userId") int userId,
			@RequestParam("followId") int followId,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		// db delete
		int rowCount = followBO.deleteFollow(userId, followId);
		
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
