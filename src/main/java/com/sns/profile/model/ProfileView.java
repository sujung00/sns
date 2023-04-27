package com.sns.profile.model;

import java.util.List;

import com.sns.post.model.Post;
import com.sns.timeline.model.SearchView;
import com.sns.user.model.User;

public class ProfileView {
	
	private User user;
	
	private boolean followed;
	
	private int postCount;
	
	private int followerCount;
	
	private List<SearchView> followerList;
	
	private int followingCount;
	
	private List<SearchView> followingList;

	private List<Post> postList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFollowed() {
		return followed;
	}

	public void setFollowed(boolean followed) {
		this.followed = followed;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}
	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	public List<SearchView> getFollowerList() {
		return followerList;
	}

	public void setFollowerList(List<SearchView> followerList) {
		this.followerList = followerList;
	}

	public List<SearchView> getFollowingList() {
		return followingList;
	}

	public void setFollowingList(List<SearchView> followingList) {
		this.followingList = followingList;
	}
}
