package vvr.onlinestore.user;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import vvr.onlinestore.utils.MailUtils;
import vvr.onlinestore.utils.UUIDUtils;

/**
 * 用户：业务逻辑
 * @author wwr
 *
 */
//开启事务
@Transactional
public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 用户注册
	 * @param user
	 */
	public void regist(User user) {

		//插入数据库
		user.setState(0); 	//0代表未激活，1代表激活
		user.setCode(UUIDUtils.getUUID());	//设置激活码
		userDao.save(user);
		
		
		//发送邮件
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
	 * 业务层，激活用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	/**
	 * 修改用户状态
	 * @param user
	 */
	public void update(User user) {

		user.setState(1);
		userDao.update(user);
	}

	/**
	 * 用户登录，查询用户名，密码和状态
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userDao.login(user);
	}

	/**
	 * 业务逻辑层，查询用户名是否已经存在
	 * @param username
	 * @return
	 */
	public User checkUsername(String username) {
		return userDao.checkUsername(username);
	}
	

}
