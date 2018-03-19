package vvr.onlinestore.product;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProductDao extends HibernateDaoSupport {

	/**
	 * 查询热门商品，首页只能显示十个，所以查询十个，使用分页查询
	 * @return
	 */
	public List<Product> findHot() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询条件
		criteria.add(Restrictions.eq("is_hot", 1));
		//从0开始查询十条数据
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	/**
	 * 查询最新商品，按时间排序，查10个
	 * @return
	 */
	public List<Product> findNew() {

		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询条件,倒序排序
		criteria.addOrder(Order.desc("pdate"));
		//从0开始查询十条数据
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

}
