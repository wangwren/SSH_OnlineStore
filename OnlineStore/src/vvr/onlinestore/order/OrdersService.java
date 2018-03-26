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

	/**
	 * ͨ��ָ��������Ų�ѯ����
	 * @param oid
	 * @return
	 */
	public Orders findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	/**
	 * �޸Ķ���
	 * @param currOrder
	 */
	public void update(Orders currOrder) {

		orderDao.update(currOrder);
	}

}
