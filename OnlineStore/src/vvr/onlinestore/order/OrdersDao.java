package vvr.onlinestore.order;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrdersDao extends HibernateDaoSupport{

	/**
	 * ���涩��
	 * @param order
	 * @return
	 */
	public Integer saveOrder(Orders order) {
		
		//save�����ɷ��ز������ɵ�id
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		
		return oid;
	}

}
