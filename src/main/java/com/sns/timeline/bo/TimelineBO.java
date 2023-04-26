package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.follow.bo.FollowBO;
import com.sns.follow.model.Follow;
import com.sns.like.bo.LikeBO;
import com.sns.like.model.Like;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.timeline.model.SearchView;
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
	
	@Autowired
	private FollowBO followBO;

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
			card.setFilledLike(likeBO.existLike(post.getId(), userId));
			
			// 내가 post작성한 사람을 follow 했는지 여부
			card.setFollowed(followBO.existFollow(userId, post.getUserId()));
			
			//!!! cardList에 채우기
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
	
	public CardView generateCard(int postId, Integer userId) {
		CardView cardView = new CardView();
		
		// post
		Post post = postBO.getPostByPostId(postId);
		cardView.setPost(post);
		
		// user
		User user = userBO.getUserByUserId(post.getUserId());
		cardView.setUser(user);
		
		// comment
		List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
		cardView.setCommentList(commentList);
		
		// like 개수
		int likeCount = likeBO.getLikeCountByPostId(post.getId());
		cardView.setLikeCount(likeCount);
		
		// 내가(로그인 된 사람) 좋아요를 눌렀는지 여부
		cardView.setFilledLike(likeBO.existLike(post.getId(), userId));

		// 내가 post작성한 사람을 follow 했는지 여부
		cardView.setFollowed(followBO.existFollow(userId, post.getUserId()));

		return cardView;
	}
	
	public List<SearchView> generateSearchViewList(Integer userId ,String search){
		List<SearchView> searchViewList = new ArrayList<>();
		
		List<User> userList = userBO.getUserListByLoginId(search);
		
		for(User user : userList) {
			SearchView searchView = new SearchView();
			
			searchView.setUser(user);
			
			searchView.setFollowed(followBO.existFollow(userId, user.getId()));
			
			searchViewList.add(searchView);
		}
		
		return searchViewList;
	}
}
