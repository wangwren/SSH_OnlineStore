package vvr.onlinestore.user;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import vvr.onlinestore.utils.MailUtils;
import vvr.onlinestore.utils.UUIDUtils;

/**
 * �û���ҵ���߼�
 * @author wwr
 *
 */
//��������
@Transactional
public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * �û�ע��
	 * @param user
	 */
	public void regist(User user) {

		//�������ݿ�
		user.setState(0); 	//0����δ���1������
		user.setCode(UUIDUtils.getUUID());	//���ü�����
		userDao.save(user);
		
		
		//�����ʼ�
		try {
			MailUtils.sendMail(user.getEmail(), user.getCode());
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * ҵ��㣬�����û�
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	/**
	 * �޸��û�״̬
	 * @param user
	 */
	public void update(User user) {

		user.setState(1);
		userDao.update(user);
	}

	/**
	 * �û���¼����ѯ�û����������״̬
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userDao.login(user);
	}

	/**
	 * ҵ���߼��㣬��ѯ�û����Ƿ��Ѿ�����
	 * @param username
	 * @return
	 */
	public User checkUsername(String username) {
		return userDao.checkUsername(username);
	}
	

}
