package com.sns.follow.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.follow.model.Follow;

@Repository
public interface FollowMapper {
	
	public int selectFollowCountByUserIdFollowId(
			@Param("userId") int userId,
			@Param("followId") int followId);

	public int insertFollow(
			@Param("userId") int userId,
			@Param("followId") int followId);
	
	public int deleteFollow(
			@Param("userId") int userId,
			@Param("followId") int followId);
	
	public Follow selectFollowByUserIdFollowId(
			@Param("userId") int userId,
			@Param("followId") int followId);
}
