package vvr.onlinestore.user;

import org.springframework.transaction.annotation.Transactional;

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
		
	}
	

}
