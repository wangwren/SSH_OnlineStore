package vvr.onlinestore.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport{

	/**
	 * 用户注册，插入数据库
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 激活用户，查询出激活码用户
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
	 * dao层修改用户状态
	 * @param user
	 */
	public void update(User user) {

		this.getHibernateTemplate().update(user);
	}

	/**
	 * 用户登录
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
	 * dao层查询用户名是否存在
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
