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

@SuppressWarnings("all")//���ƾ���(ȥ����)
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	@Resource(name="userService")
	private UserService userService;
	
	private User user = new User();

	private String checkcode;

	/**
	 * ��ת��ע��ҳ���ִ�еķ���
	 */
	public String registPage() {
		return "registPage";
	}
	
	/**
	 * Ajax�첽��ѯ�Ƿ�����û���
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		User existUser = userService.findByUserName(user.getUserName());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (existUser != null) {
			out.println("<font color='red'>�û����Ѿ����ڣ�</font>");
		}else {
			out.println("<font color='green'>���û������ã�</font>");
		}
		return NONE;
	}
	
	/**
	 * �û�ע��
	 * ע��ǰУ���û����Ƿ�Ψһ
	 */
	public void validateRegist() {
		User existUser = userService.findByUserName(user.getUserName());
		if (existUser != null) {
			addFieldError("userName", "�û����Ѿ����ڣ�");
		}
	}
	public String regist() {
		//�ж���֤��
		//��session�л����֤������ֵ
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode1.equalsIgnoreCase(checkcode)) {
			this.addFieldError("checkcodeInfo", "��֤���������");
			return "registPage";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�����ȥ�����м����˺ţ�");
		return "msg";
	}
	
	/**
	 * �û�����
	 */
	public String active() {
		//���ݼ������ѯ�û�
		User existUser = userService.findByUserCode(user.getCode());
		if (existUser == null) {
			this.addActionMessage("����ʧ�ܣ����������");
		}else {
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ�����ȥ��¼��");
		}
		return "msg";
	}
	
	/**
	 * ��ת����¼ҳ���ִ�з���
	 */
	public String loginPage() {
		return "loginPage";
	}
	
	/**
	 * �û���¼
	 */
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("���������˺�δ���");
			return "login";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	
	/**
	 * �û��˳�
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
