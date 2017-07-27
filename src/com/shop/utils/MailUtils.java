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
	 * �����ʼ��ķ���
	 * @param to :�ռ���
	 * @param code :������
	 */
	
	public static void sendMail(String recipient,String code) {
		/**
		 * 1.���һ��Session����
		 * 2.����һ�������ʼ��Ķ���Message��
		 * 3.�����ʼ�Transport��
		 */
		String userName = "ridiculousp@163.com";
		String password = "ridiculous163";
		
		String sender = "ridiculousp@163.com";
		String title = "����Ridiculous�̳��ٷ������ʼ�";
		String content = "<h1>Ridiculous�̳��ٷ������ʼ�!</h1>"
				+ "<h3><a href='http://192.168.179.28:8080/shop/user_active.action?code="+code+"'>������Ӽ����˺�</a></h3>";
		
		//1.������Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
			
		});
		//2.�����ʼ�����
		Message message = new MimeMessage(session);
		 try {
			//���÷�����
			message.setFrom(new InternetAddress(sender));
			//�����ռ���(recipient)		//����:CC		����:BCC
			message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
			//���ñ���
			message.setSubject(title);
			//��������(���ı�:text/plain)
			message.setContent(content, "text/html;charset=UTF-8");
			//3.�����ʼ�
			Transport.send(message);
			System.out.println("--------To:"+recipient+"�ʼ����ͳɹ���--------");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
