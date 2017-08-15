package com.shop.adminUser.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.adminUser.entity.AdminUser;
import com.shop.adminUser.service.AdminUserService;

@SuppressWarnings("all")
@Component("adminUserAction")
@Scope("prototype")
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	
	private AdminUser adminUser = new AdminUser();
	
	@Resource(name="adminUserService")
	private AdminUserService adminUserService;
	
	//后台登录方法
	public String login() {
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			this.addActionError("亲！您的用户名或密码错误！");
			return "loginError";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	
	
	
	
	
	
	
	
	public AdminUser getModel() {
		return adminUser;
	}
	
}
