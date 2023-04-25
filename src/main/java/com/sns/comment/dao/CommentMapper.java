package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentMapper {

	public int insertComment(
			@Param("postId") int postId, 
			@Param("userId") int userId,
			@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public int deleteComment(int commentId);
	
	public void deleteCommentByPostId(int postId);
}
