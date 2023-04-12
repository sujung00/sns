package com.sns.user.dao;

import com.sns.user.model.User;

public interface UserMapper {

	public User selectUserByLoginId(String loginId);
}
