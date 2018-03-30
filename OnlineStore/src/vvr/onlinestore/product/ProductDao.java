package vvr.onlinestore.product;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import vvr.onlinestore.utils.PageHibernateCallback;

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

	/**
	 * 查询一级分类对应的商品总记录数
	 * @return
	 */
	public int findTotal(Integer cid) {
		
		//多表查询
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Product p,CategorySecond cs where p.categorySecond=cs and cs.category.cid=?",cid);

		int total = list.get(0).intValue();
		
		return total;
	}

	/**
	 * 查询一级分类对应的全部商品
	 * @param cid
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Product> findProductToCid(Integer cid, Integer page, int limit) {
		
		String hql = "select p from Product p,CategorySecond cs where p.categorySecond=cs and cs.category.cid=?";
		Object[] params = {cid};
		int start = (page - 1) * limit;
		
		//又一种分页查询，调用executeFind方法，自己编写继承HibernateCallback的类
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, params, start, limit));
		if(list.size() > 0) {
			return list;
		}
		
		return null;
	}

	/**
	 * 查询某个商品的详细信息
	 * @return
	 */
	public Product findById(int pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * 查询二级分类下对应的商品总数
	 * @param csid
	 * @return
	 */
	public int findTotalByCsid(Integer csid) {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Product p,CategorySecond cs where p.categorySecond=cs and cs.csid=?", csid);
		int total = list.get(0).intValue();
		return total;
	}

	/**
	 * 查询二级分类下对应的商品
	 * @param csid
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Product> findByCsid(Integer csid, Integer page, int limit) {
		
		String hql = "select p from Product p,CategorySecond cs where p.categorySecond=cs and cs.csid=?";
		int start = (page - 1) * limit;
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{csid}, start, limit));
		
		return list;
	}

	/**
	 * 后台查询所有商品记录数
	 * @return
	 */
	public Integer findTotal() {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Product");
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * 查询所有商品并分页
	 * @return
	 */
	public List<Product> findAllProduct(Integer begin,Integer limit) {
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("from Product", null, begin, limit));
		if(list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 后台添加商品
	 * @param product
	 */
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/**
	 * 查询指定产品
	 * @param pid
	 * @return
	 */
	public Product findByPid(int pid) {
		//Product product = this.getHibernateTemplate().get(Product.class, pid);
		List<Product> list = this.getHibernateTemplate().find("from Product where pid=?", pid);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 删除产品
	 * @param pro
	 */
	public void delete(Product pro) {
		this.getHibernateTemplate().delete(pro);
	}
}
