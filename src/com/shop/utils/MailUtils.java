package com.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	
	/**
	 * 发行邮件的方法
	 * @param to :收件人
	 * @param code :激活码
	 */
	
	public static void sendMail(String recipient,String code) {
		/**
		 * 1.获得一个Session对象。
		 * 2.创建一个代表邮件的对象Message。
		 * 3.发送邮件Transport。
		 */
		String userName = "ridiculousp@163.com";
		String password = "ridiculous163";
		
		String sender = "ridiculousp@163.com";
		String title = "来自Ridiculous商场官方激活邮件";
		String content = "<h1>Ridiculous商场官方激活邮件!</h1>"
				+ "<h3><a href='http://192.168.179.28:8080/shop/user_active.action?code="+code+"'>点此链接激活账号</a></h3>";
		
		//1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
			
		});
		//2.创建邮件对象
		Message message = new MimeMessage(session);
		 try {
			//设置发件人
			message.setFrom(new InternetAddress(sender));
			//设置收件人(recipient)		//抄送:CC		密送:BCC
			message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
			//设置标题
			message.setSubject(title);
			//设置正文(纯文本:text/plain)
			message.setContent(content, "text/html;charset=UTF-8");
			//3.发送邮件
			Transport.send(message);
			System.out.println("--------To:"+recipient+"邮件发送成功！--------");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
