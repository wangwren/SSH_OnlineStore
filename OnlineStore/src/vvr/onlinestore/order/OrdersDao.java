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

	/**
	 * ͨ��ָ��������Ų�ѯ����
	 * @param oid
	 * @return
	 */
	public Orders findByOid(Integer oid) {
		
		Orders order = this.getHibernateTemplate().get(Orders.class, oid);

		return order;
	}

	/**
	 * �޸Ķ���
	 * @param currOrder
	 */
	public void update(Orders currOrder) {

		this.getHibernateTemplate().update(currOrder);
	}

}
