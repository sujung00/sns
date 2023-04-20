package com.sns.follow.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowMapper {

	public int insertFollow(
			@Param("userId") int userId,
			@Param("followId") int followId);
	
	public int deleteFollow(
			@Param("userId") int userId,
			@Param("followId") int followId);
}
