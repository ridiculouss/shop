package com.shop.adminUser.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.shop.adminUser.dao.AdminUserDao;
import com.shop.adminUser.entity.AdminUser;

@Component("adminUserDao")
@SuppressWarnings("unchecked")
public class AdminUserDaoImpl implements AdminUserDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = (List<AdminUser>) hibernateTemplate.find(hql, adminUser.getUsername(),
				adminUser.getPassword());
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
