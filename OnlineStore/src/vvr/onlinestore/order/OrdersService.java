package vvr.onlinestore.order;

import org.springframework.transaction.annotation.Transactional;

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

}
