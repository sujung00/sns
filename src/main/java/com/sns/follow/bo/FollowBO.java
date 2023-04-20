package com.sns.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowMapper;

@Service
public class FollowBO {
	
	@Autowired
	private FollowMapper followMapper;

	public int addFollow(int userId, int followId) {
		return followMapper.insertFollow(userId, followId);
	}
	
	public int deleteFollow(int userId, int followId) {
		return followMapper.deleteFollow(userId, followId);
	}
}
