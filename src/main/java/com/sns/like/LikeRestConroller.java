package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;
import com.sns.like.dao.LikeMapper;

@RestController
public class LikeRestConroller {

	@Autowired
	private LikeBO likeBO;
	
	// GET : /like?postId=13 @RequestParam
	// GET or POST : /like/{postId} @PathVariable
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session) {
		
		// 응답
		
		// 있는지 없는지 판단
		// 있으면 db insert 없으면 db delete => LikeBO
		// BO호츌 => BO 안에서 like 여부 체크
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null) {
			result.put("code", 300);
			result.put("errorMessage", "로그인 후 사용가능합니다");
			
			return result;
		}
		
		int rowCount = likeBO.likeToggle(postId, userId);
		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "좋아요 실패");
		}
		
		return result;
	}
}
