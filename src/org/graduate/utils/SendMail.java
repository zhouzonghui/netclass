package org.graduate.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送密码邮件的线程类
 * @author Owner
 */
public class SendMail extends Thread {
	/**
	 * 接收传递过来的学生的邮箱，以便给这个邮箱发邮件
	 */
	private String email;
	/**
	 * 接收传递过来的临时密码
	 */
	private String pass;
	
	/**
	 * 服务器用来发邮件的邮箱
	 */
	private String mMail = "netclass2015@sina.com";
	/**
	 * 服务器用来发邮件的邮箱用户名
	 */
	private String mUser = "netclass2015";
	/**
	 * 服务器用来发邮件的邮箱密码
	 */
	private String mPass = "netclass";
	
	/**
	 * 126邮箱服务器的地址
	 */
	private String host = "smtp.sina.com";
	
	public SendMail(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}


	@Override
	public void run() {
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.host", host);
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.smtp.auth", "true");
			
			Session session = Session.getInstance(properties);
			Transport transport = session.getTransport();
			transport.connect(host, mUser, mPass);
			Message message = makeMessage(session, email);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Message makeMessage(Session session, String mail) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mMail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			message.setSubject("密码重置邮件");
			String info = "您重置后的密码为" + pass + "，请及时使用此密码登录，然后将其修改为自己的密码！<br/>"
					+ "<font size='5' color='red'>请勿向他人泄露！</font>";
			
			message.setContent(info, "text/html;charset=UTF-8");
			message.saveChanges();
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}















