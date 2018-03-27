package vvr.onlinestore.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vvr.onlinestore.user.User;

@Transactional
public class OrdersService {
	
	private OrdersDao orderDao;
	

	public void setOrderDao(OrdersDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	public Integer saveOrder(Orders order) {
		
		return orderDao.saveOrder(order);
	}

	/**
	 * 通过指定订单编号查询订单
	 * @param oid
	 * @return
	 */
	public Orders findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	/**
	 * 修改订单
	 * @param currOrder
	 */
	public void update(Orders currOrder) {

		orderDao.update(currOrder);
	}

	/**
	 * 查询用户订单
	 * @param user
	 * @return
	 */
	public List<Orders> findByUid(User user) {
		return orderDao.findByUid(user);
	}

}
