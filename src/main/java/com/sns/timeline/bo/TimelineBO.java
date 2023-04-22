package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.like.model.Like;
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
	
	@Autowired
	private LikeBO likeBO;

	// 비로그인도 카드 리스트가 보여져야 하기 때문에 userId는 null 가능
	public List<CardView> generateCardList(Integer userId) {
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
			
			// like 개수
			int likeCount = likeBO.getLikeCountByPostId(post.getId());
			card.setLikeCount(likeCount);
			
			// 내가(로그인 된 사람) 좋아요를 눌렀는지 여부
			if (userId == null) {
				card.setFilledLike(false);
			} else {
				Like like = likeBO.getLikeByPostIdUserId(post.getId(), userId);
				if(like == null) {
					card.setFilledLike(false);
				} else {
					card.setFilledLike(true);
				}
			}
			
			// follow
			
			//!!! cardList에 채우기
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}
