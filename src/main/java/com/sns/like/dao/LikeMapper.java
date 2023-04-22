package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.model.Like;

@Repository
public interface LikeMapper {

	public int selectLikeCount(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int insertLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public Like selectLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int selectLikeCountByPostId(int postId);
}
