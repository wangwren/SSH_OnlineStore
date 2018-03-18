package vvr.onlinestore.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport{

	/**
	 * �û�ע�ᣬ�������ݿ�
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * �����û�����ѯ���������û�
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {

		List<User> list = this.getHibernateTemplate().find("from User where code = ?", code);		
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * dao���޸��û�״̬
	 * @param user
	 */
	public void update(User user) {

		this.getHibernateTemplate().update(user);
	}

	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public User login(User user) {
		
		List<User> list = this.getHibernateTemplate().find("from User where username=? and password=? and state=?", user.getUsername(),user.getPassword(),1);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * dao���ѯ�û����Ƿ����
	 * @param username
	 * @return
	 */
	public User checkUsername(String username) {
		
		List<User> list = this.getHibernateTemplate().find("from User where username=?", username);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
