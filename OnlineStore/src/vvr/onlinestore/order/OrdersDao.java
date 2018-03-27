package vvr.onlinestore.order;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import vvr.onlinestore.user.User;

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

	/**
	 * ��ѯ�û�����
	 * @param user
	 * @return
	 */
	public List<Orders> findByUid(User user) {
		
		List<Orders> list = this.getHibernateTemplate().find("from Orders o where o.user.uid=?", user.getUid());
		if(list.size() > 0) {
			return list;
		}
		return null;
	}

}
