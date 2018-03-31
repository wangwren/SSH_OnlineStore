package vvr.onlinestore.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vvr.onlinestore.user.User;
import vvr.onlinestore.utils.PageBean;

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

	/**
	 * 后台查询所有订单
	 * @param page
	 * @return
	 */
	public PageBean<Orders> findAll(Integer page) {
		PageBean<Orders> pageBean = new PageBean<Orders>();
		pageBean.setPage(page);
		Integer limit = 10;
		pageBean.setLimit(limit);
		Integer total = orderDao.findTotal();
		pageBean.setTotal(total);
		Integer totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = total / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		Integer begin = (page - 1) * limit;
		List<Orders> list = orderDao.findAll(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 后台查询指定状态订单
	 * @param state
	 * @param page
	 * @return
	 */
	public PageBean<Orders> findByState(Integer state, Integer page) {
		PageBean<Orders> pageBean = new PageBean<Orders>();
		pageBean.setPage(page);
		Integer limit = 10;
		pageBean.setLimit(limit);
		Integer total = orderDao.findTotal(state);
		pageBean.setTotal(total);
		Integer totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = total / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		Integer begin = (page - 1) * limit;
		List<Orders> list = orderDao.findAll(begin,limit,state);
		pageBean.setList(list);
		return pageBean;
	}

}
