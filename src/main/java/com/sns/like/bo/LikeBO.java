package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;
import com.sns.like.model.Like;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;

	public int likeToggle(int postId, int userId) {
		//like 여부 체크
		int rowCount = likeMapper.selectLikeCountByPostIdOrUserId(postId, userId);
		
		if(rowCount > 0) {
			// 좋아요 해제
			return likeMapper.deleteLikeByPostIdUserId(postId, userId);
			
		} else {
			// 좋아요
			return likeMapper.insertLikeByPostIdUserId(postId, userId);
		}
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	public boolean existLike(int postId, Integer userId) {
		// 비로그인
		if(userId == null) {
			return false;
		}
		
		// 로그인
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
	}
	
	public void deleteLikeByPostId(int postId) {
		likeMapper.deleteLikeByPostId(postId);
	}
}
