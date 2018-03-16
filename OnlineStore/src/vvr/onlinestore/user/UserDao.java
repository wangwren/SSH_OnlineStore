package vvr.onlinestore.user;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport{

	//用户注册，插入数据库
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

}
