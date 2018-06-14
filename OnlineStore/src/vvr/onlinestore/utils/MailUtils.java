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
 * 邮件发送工具类
 * @author wwr
 *
 */
public class MailUtils {
	/**
	 * 
	 * @param to   收件人
	 * @param code	激活码 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void sendMail(String to,String code) throws AddressException, MessagingException {
		
		Properties props = new Properties();
		props.setProperty("mail.smtp", "localhost");
		
		//1.Session对象，连接邮箱服务器,这里的session是javax.mail中的
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@store.com", "123");
			}
			
		});
		
		
		//2.构建邮件信息
		Message message = new MimeMessage(session);
		
		//发件人
		message.setFrom(new InternetAddress("service@store.com"));
		
		//收件人,第一个参数代表类型，即发送邮件，还有抄送和密送。第二个参数代表收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		
		//设置标题
		message.setSubject("来自ONLINE STORE的激活邮件");
		
		//设置正文,第一个参数代表邮件内容。第二个参数代表邮件格式，a href='http://172.20.10.2:8080/  写的是本机的ip地址
		message.setContent("<h1>来自ONLINE STORE的官网激活邮件</h1><h3>请点击下方链接来激活您的账号，若不是您本人操作请忽略该邮件</h3><h5><a href='http://localhost:8080/OnlineStore/user_active.action?code="+code+"'>http://172.20.10.2:8080/OnlineStore/user_active.action?code="+code+"</a></h5>", "text/html;charset=UTF-8");
		
		//3.发送对象
		Transport.send(message);
	}
}
