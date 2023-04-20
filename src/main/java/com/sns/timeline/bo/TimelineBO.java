package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;

	public List<CardView> generateCardList() {
		List<CardView> cardViewList = new ArrayList<>(); // []
		
		// postList => cardViewList
		// 글 목록을 가져온다.
		List<Post> postList = postBO.getPostList();
		
		// postList 반복문 돌린다. => 1:1 Post -> CardView => cardViewList에 넣는다.
		for(Post post : postList) {
			CardView card = new CardView();
			
			// post
			card.setPost(post);
			
			// user
			User user = userBO.getUserByUserId(post.getUserId());
			card.setUser(user);
			
			// comment
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commentList);
			
			// like
			
			// follow
			
			//!!! cardList에 채우기
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}
