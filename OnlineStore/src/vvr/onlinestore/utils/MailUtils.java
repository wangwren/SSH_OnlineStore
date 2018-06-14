package vvr.onlinestore.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * �ʼ����͹�����
 * @author wwr
 *
 */
public class MailUtils {
	/**
	 * 
	 * @param to   �ռ���
	 * @param code	������ 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendMail(String to,String code) throws AddressException, MessagingException {
		
		Properties props = new Properties();
		props.setProperty("mail.smtp", "localhost");
		
		//1.Session�����������������,�����session��javax.mail�е�
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@store.com", "123");
			}
			
		});
		
		
		//2.�����ʼ���Ϣ
		Message message = new MimeMessage(session);
		
		//������
		message.setFrom(new InternetAddress("service@store.com"));
		
		//�ռ���,��һ�������������ͣ��������ʼ������г��ͺ����͡��ڶ������������ռ���
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		
		//���ñ���
		message.setSubject("����ONLINE STORE�ļ����ʼ�");
		
		//��������,��һ�����������ʼ����ݡ��ڶ������������ʼ���ʽ��a href='http://172.20.10.2:8080/  д���Ǳ�����ip��ַ
		message.setContent("<h1>����ONLINE STORE�Ĺ��������ʼ�</h1><h3>�����·����������������˺ţ������������˲�������Ը��ʼ�</h3><h5><a href='http://localhost:8080/OnlineStore/user_active.action?code="+code+"'>http://172.20.10.2:8080/OnlineStore/user_active.action?code="+code+"</a></h5>", "text/html;charset=UTF-8");
		
		//3.���Ͷ���
		Transport.send(message);
	}
}
