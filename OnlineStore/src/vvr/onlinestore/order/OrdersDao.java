package vvr.onlinestore.order;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import vvr.onlinestore.user.User;

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

	/**
	 * 通过指定订单编号查询订单
	 * @param oid
	 * @return
	 */
	public Orders findByOid(Integer oid) {
		
		Orders order = this.getHibernateTemplate().get(Orders.class, oid);

		return order;
	}

	/**
	 * 修改订单
	 * @param currOrder
	 */
	public void update(Orders currOrder) {

		this.getHibernateTemplate().update(currOrder);
	}

	/**
	 * 查询用户订单
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
