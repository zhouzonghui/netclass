package org.graduate.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * ���������ʼ����߳���
 * @author Owner
 */
public class SendMail extends Thread {
	/**
	 * ���մ��ݹ�����ѧ�������䣬�Ա��������䷢�ʼ�
	 */
	private String email;
	/**
	 * ���մ��ݹ�������ʱ����
	 */
	private String pass;
	
	/**
	 * �������������ʼ�������
	 */
	private String mMail = "netclass2015@sina.com";
	/**
	 * �������������ʼ��������û���
	 */
	private String mUser = "netclass2015";
	/**
	 * �������������ʼ�����������
	 */
	private String mPass = "netclass";
	
	/**
	 * 126����������ĵ�ַ
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
			message.setSubject("���������ʼ�");
			String info = "�����ú������Ϊ" + pass + "���뼰ʱʹ�ô������¼��Ȼ�����޸�Ϊ�Լ������룡<br/>"
					+ "<font size='5' color='red'>����������й¶��</font>";
			
			message.setContent(info, "text/html;charset=UTF-8");
			message.saveChanges();
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}















