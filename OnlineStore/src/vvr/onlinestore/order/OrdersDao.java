package vvr.onlinestore.order;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import vvr.onlinestore.user.User;
import vvr.onlinestore.utils.PageHibernateCallback;

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

	/**
	 * ��̨��ѯȫ����������
	 * @return
	 */
	public Integer findTotal() {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Orders");
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * ��̨��ѯȫ������
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Orders> findAll(Integer begin, Integer limit) {
		List<Orders> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Orders>("from Orders order by ordertime desc", null, begin, limit));
		if(list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * ��̨��ѯָ��״̬�Ķ�������
	 * @param state
	 * @return
	 */
	public Integer findTotal(Integer state) {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Orders where state=?",state);
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * ��̨��ѯָ��״̬�Ķ���
	 * @param begin
	 * @param limit
	 * @param state
	 * @return
	 */
	public List<Orders> findAll(Integer begin, Integer limit, Integer state) {
		List<Orders> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Orders>("from Orders where state = ? order by ordertime desc", new Object[] {state}, begin, limit));
		if(list.size() > 0) {
			return list;
		}
		return null;
	}

}
