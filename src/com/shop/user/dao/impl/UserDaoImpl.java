package com.shop.user.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.shop.user.dao.UserDao;
import com.shop.user.entity.User;

@Component("userDao")
@SuppressWarnings("all")
public class UserDaoImpl implements UserDao{

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public User findByUserName(String userName) {
		String hql = "from User where u_userName = ?";
		List<User> list = (List<User>) hibernateTemplate.find(hql, userName);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void save(User user) {
		hibernateTemplate.save(user);
	}

	public User findByUserCode(String code) {
		String hql = "from User where u_code = ?";
		List<User> list = (List<User>) hibernateTemplate.find(hql, code);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void update(User user) {
		hibernateTemplate.update(user);
	}

	public User login(User user) {
		String hql = "from User where u_userName = ? and u_password = ? and u_state = ?";
		List<User> list = (List<User>) hibernateTemplate.find(hql, user.getUserName(),user.getPassword(),1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
