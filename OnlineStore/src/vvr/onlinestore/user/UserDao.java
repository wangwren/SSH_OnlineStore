package vvr.onlinestore.user;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport{

	//�û�ע�ᣬ�������ݿ�
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

}
