package com.sns.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowMapper;
import com.sns.follow.model.Follow;

@Service
public class FollowBO {
	
	@Autowired
	private FollowMapper followMapper;
	
	public int followToggle(int userId, int followId) {
		int rowCount = followMapper.selectFollowCountByUserIdFollowId(userId, followId);
		
		if(rowCount > 0) {
			// 언팔로우
			return followMapper.deleteFollow(userId, followId);
		} else {
			// 팔로우
			return followMapper.insertFollow(userId, followId);
		}
	}
	
	public Follow getFollowByUserIdFollowId(int userId, int followId) {
		return followMapper.selectFollowByUserIdFollowId(userId, followId);
	}
	
	public int getFollowerCountByFollowId(int followId) {
		return followMapper.selectFollowerCountByFollowId(followId);
	}

	public int getFollowingCountByUserId(int userId) {
		return followMapper.selectFollowingCountByUserId(userId);
	}
}
