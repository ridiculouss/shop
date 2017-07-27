package com.shop.user.service;

import com.shop.user.entity.User;

public interface UserService {
	
	public User findByUserName(String userName);
	
	public void save(User user);

	public User findByUserCode(String code);

	public void update(User user);

	public User login(User user);
	
}
