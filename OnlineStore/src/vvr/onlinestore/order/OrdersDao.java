package vvr.onlinestore.order;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrdersDao extends HibernateDaoSupport{

	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	public Integer saveOrder(Orders order) {
		
		//save方法可返回插入生成的id
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		
		return oid;
	}

}
