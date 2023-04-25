package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired UserBO userBO;
	
	public int addComment(int postId, int userId, String content) {
		return commentMapper.insertComment(postId, userId, content);
	}
	
	public List<CommentView> generateCommentViewListByPostId(int postId){
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 글에 해당하는 댓글들
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문
		for(Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// comment
			commentView.setComment(comment);
			
			// user
			User user = userBO.getUserByUserId(comment.getUserId());
			commentView.setUser(user);
			
			// 새로 만든 댓글 정보를 list에 담는다
			commentViewList.add(commentView);
		}
		
		// 결과 리스트에 담기
		
		return commentViewList;
	}
	
	public int deleteComment(int commentId) {
		return commentMapper.deleteComment(commentId) ;
	}
	
	public void deleteCommentByPostId(int postId) {
		commentMapper.deleteCommentByPostId(postId);
	}
}
