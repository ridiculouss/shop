package com.shop.adminUser.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shop.adminUser.dao.AdminUserDao;
import com.shop.adminUser.entity.AdminUser;
import com.shop.adminUser.service.AdminUserService;

@Component("adminUserService")
@Transactional
public class AdminUserServiceImpl implements AdminUserService{
	
	@Resource(name="adminUserDao")
	private AdminUserDao adminUserDao;

	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
}
