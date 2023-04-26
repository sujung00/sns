package com.sns.timeline.model;

import com.sns.user.model.User;

public class SearchView {

	private User user;
	
	// login된 user 기준 follow 여부 boolean
	private boolean followed;

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
}
