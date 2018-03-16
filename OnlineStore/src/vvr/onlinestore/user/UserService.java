package vvr.onlinestore.user;

import org.springframework.transaction.annotation.Transactional;

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
		
	}
	

}
