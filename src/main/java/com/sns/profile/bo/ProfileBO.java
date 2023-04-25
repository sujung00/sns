package com.sns.profile.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.bo.FollowBO;
import com.sns.follow.model.Follow;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.profile.model.ProfileView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class ProfileBO {

	@Autowired
	private UserBO userBO;

	@Autowired
	private FollowBO followBO;
	
	@Autowired
	private PostBO postBO;

	public ProfileView generateProfileView(Integer userId, String LoginId) {
		ProfileView profileView = new ProfileView();

		// user
		User user = userBO.getUserByLoginId(LoginId);
		profileView.setUser(user);

		// 내가 profile user를 follow 했는지 여부
		profileView.setFollowed(followBO.existFollow(userId, user.getId()));

		// post 개수
		int postCount = postBO.getPostCountByUserId(user.getId());
		profileView.setPostCount(postCount);

		// 팔로워 수
		int followerCount = followBO.getFollowerCountByFollowId(user.getId());
		profileView.setFollowerCount(followerCount);

		// 팔로잉 수
		int followingCount = followBO.getFollowingCountByUserId(user.getId());
		profileView.setFollowingCount(followingCount);

		// postList
		List<Post> postList = postBO.getPostListByUserId(user.getId());
		profileView.setPostList(postList);

		return profileView;
	}
}
