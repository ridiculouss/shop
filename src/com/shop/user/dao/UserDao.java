package com.shop.user.dao;

import com.shop.user.entity.User;

public interface UserDao {
	
	public User findByUserName(String userName);
	
	public void save(User user);
	
	public User findByUserCode(String code);

	public void update(User user);

	public User login(User user);
	
}
