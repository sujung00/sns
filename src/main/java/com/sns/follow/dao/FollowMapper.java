package com.sns.follow.dao;

import java.util.List;

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
	
	public int selectFollowerCountByFollowId(int followId);
	
	public int selectFollowingCountByUserId(int userId);
	
	public List<Follow> selectFollowerListByFollowId(int followId);
	
	public List<Follow> selectFollowingListByUserId(int userId);
}
