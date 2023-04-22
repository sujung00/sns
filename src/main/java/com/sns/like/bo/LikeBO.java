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
		int rowCount = likeMapper.selectLikeCount(postId, userId);
		
		if(rowCount > 0) {
			// 좋아요 해제
			return likeMapper.deleteLikeByPostIdUserId(postId, userId);
			
		} else {
			// 좋아요
			return likeMapper.insertLikeByPostIdUserId(postId, userId);
		}
	}
	
	public Like getLikeByPostIdUserId(int postId, int userId) {
		return likeMapper.selectLikeByPostIdUserId(postId, userId);
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostId(postId);
	}
}
