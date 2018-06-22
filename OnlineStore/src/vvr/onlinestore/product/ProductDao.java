package vvr.onlinestore.product;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
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

	/**
	 * 修改商品
	 * @param product
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
	
	
	/**
	 * 查询指定商品库存
	 */
	public Integer findSize(Integer id,String size) {
		
		//String hql = "select s." + size + "Size from RelationShip as rs,Size as s WHERE rs.id=s.id AND rs.product.pid=?";
		String hql = "select " + size + "Size from Size s where s.product.pid = ?";
		List<Integer> num = this.getHibernateTemplate().find(hql, id);
		if(num.size() > 0) {
			//System.out.println(num.get(0));
			return num.get(0);
		}
		return null;
	}
	
	/**
	 * 更新尺寸表的指定列
	 * @param sizeColumn
	 * @param size
	 */
	public void updateSize(String sizeColumn,Size size) {
		
		this.getHibernateTemplate().update(sizeColumn, size);
	}
	
	/**
	 * 查询指定商品编号对应尺码表的id
	 * @param id
	 * @return
	 */
	public Integer findSizeId(Integer id) {
		
		String hql = "select id from Size where product.pid = ?";
		List<Integer> sid = this.getHibernateTemplate().find(hql, id);
		if(sid.size() > 0) {
			return sid.get(0);
		}
		
		return null;
	}
	
	/**
	 * 通过id查询指定商品的尺寸库存
	 * @param id
	 * @return
	 */
	public Size findSizeByPid(Integer id) {
		
		String hql = "from Size where product.pid = ?";
		List<Size> size = this.getHibernateTemplate().find(hql, id);
		if(size.size() > 0) {
			
			return size.get(0);
		}
		return null;
	}
	
	/**
	 * 库存跟新
	 * @param size
	 */
	public void updateSize(Size size) {
		this.getHibernateTemplate().update(size);
	}
	
	
	/**
	 * 通过商品id查询所属的一级分类
	 * @param pid
	 * @return
	 */
	public Integer findCidByPid(Integer pid) {
		
		String hql = "SELECT cs.category.cid FROM Product p,CategorySecond cs WHERE p.categorySecond.csid = cs.csid AND p.pid = ?";
		
		//多表查询
		List<Integer> cid = this.getHibernateTemplate().find(hql,pid);

		if(cid.size() > 0) {
			return cid.get(0);
		}
		
		return null;
	}
	
	public void updateOrSaveSize(Size size) {
		
		this.getHibernateTemplate().saveOrUpdate(size);
	}
}
