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
	
	//��̨��¼����
	public String login() {
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			this.addActionError("�ף������û������������");
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
