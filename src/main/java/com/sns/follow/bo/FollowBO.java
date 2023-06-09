package com.sns.follow.bo;

import java.util.List;

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
	
	public int getFollowerCountByFollowId(int followId) {
		return followMapper.selectFollowerCountByFollowId(followId);
	}
	
	public List<Follow> getFollowerListByFollowId(int followId){
		return followMapper.selectFollowerListByFollowId(followId);
	}

	public int getFollowingCountByUserId(int userId) {
		return followMapper.selectFollowingCountByUserId(userId);
	}
	
	public List<Follow> getFollowingListByUserId(int userId){
		return followMapper.selectFollowingListByUserId(userId);
	}
	
	public boolean existFollow(Integer userId, int followId) {
		if(userId == null) {
			return false;
		}
		
		return followMapper.selectFollowCountByUserIdFollowId(userId, followId) > 0;
	}
}
