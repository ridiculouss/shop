package com.shop.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.user.entity.User;
import com.shop.user.service.UserService;

@SuppressWarnings("all")//抑制警告(去黄线)
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	@Resource(name="userService")
	private UserService userService;
	
	private User user = new User();

	private String checkcode;

	/**
	 * 跳转到注册页面的执行的方法
	 */
	public String registPage() {
		return "registPage";
	}
	
	/**
	 * Ajax异步查询是否存在用户名
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		User existUser = userService.findByUserName(user.getUserName());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (existUser != null) {
			out.println("<font color='red'>用户名已经存在！</font>");
		}else {
			out.println("<font color='green'>该用户名可用！</font>");
		}
		return NONE;
	}
	
	/**
	 * 用户注册
	 * 注册前校验用户名是否唯一
	 */
	public void validateRegist() {
		User existUser = userService.findByUserName(user.getUserName());
		if (existUser != null) {
			addFieldError("userName", "用户名已经存在！");
		}
	}
	public String regist() {
		//判断验证码
		//从session中获得验证码的随机值
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode1.equalsIgnoreCase(checkcode)) {
			this.addFieldError("checkcodeInfo", "验证码输入错误！");
			return "registPage";
		}
		userService.save(user);
		this.addActionMessage("注册成功！请去邮箱中激活账号！");
		return "msg";
	}
	
	/**
	 * 用户激活
	 */
	public String active() {
		//根据激活码查询用户
		User existUser = userService.findByUserCode(user.getCode());
		if (existUser == null) {
			this.addActionMessage("激活失败！激活码错误！");
		}else {
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功！请去登录！");
		}
		return "msg";
	}
	
	/**
	 * 跳转到登录页面的执行方法
	 */
	public String loginPage() {
		return "loginPage";
	}
	
	/**
	 * 用户登录
	 */
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("密码错误或账号未激活！");
			return "login";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	
	/**
	 * 用户退出
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	
	public User getModel() {
		return user;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

}
