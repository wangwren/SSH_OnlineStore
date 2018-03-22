package vvr.onlinestore.order;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OrdersService {
	
	private OrdersDao orderDao;
	

	public void setOrderDao(OrdersDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * ���涩��
	 * @param order
	 * @return
	 */
	public Integer saveOrder(Orders order) {
		
		return orderDao.saveOrder(order);
	}

}
