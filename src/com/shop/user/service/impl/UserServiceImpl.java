package com.shop.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shop.user.dao.UserDao;
import com.shop.user.entity.User;
import com.shop.user.service.UserService;
import com.shop.utils.MailUtils;
import com.shop.utils.UUIDUtils;

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	public void save(User user) {
		user.setState(0); //0:代表未激活，1:代表已激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮箱
		MailUtils.sendMail(user.getEmail(), code);
	}

	public User findByUserCode(String code) {
		return userDao.findByUserCode(code);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public User login(User user) {
		return userDao.login(user);
	}

}
