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
	 * ��ѯ������Ʒ����ҳֻ����ʾʮ�������Բ�ѯʮ����ʹ�÷�ҳ��ѯ
	 * @return
	 */
	public List<Product> findHot() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//��ѯ����
		criteria.add(Restrictions.eq("is_hot", 1));
		//��0��ʼ��ѯʮ������
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	/**
	 * ��ѯ������Ʒ����ʱ�����򣬲�10��
	 * @return
	 */
	public List<Product> findNew() {

		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//��ѯ����,��������
		criteria.addOrder(Order.desc("pdate"));
		//��0��ʼ��ѯʮ������
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		return list;
	}

	/**
	 * ��ѯһ�������Ӧ����Ʒ�ܼ�¼��
	 * @return
	 */
	public int findTotal(Integer cid) {
		
		//����ѯ
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Product p,CategorySecond cs where p.categorySecond=cs and cs.category.cid=?",cid);

		int total = list.get(0).intValue();
		
		return total;
	}

	/**
	 * ��ѯһ�������Ӧ��ȫ����Ʒ
	 * @param cid
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Product> findProductToCid(Integer cid, Integer page, int limit) {
		
		String hql = "select p from Product p,CategorySecond cs where p.categorySecond=cs and cs.category.cid=?";
		Object[] params = {cid};
		int start = (page - 1) * limit;
		
		//��һ�ַ�ҳ��ѯ������executeFind�������Լ���д�̳�HibernateCallback����
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, params, start, limit));
		if(list.size() > 0) {
			return list;
		}
		
		return null;
	}

	/**
	 * ��ѯĳ����Ʒ����ϸ��Ϣ
	 * @return
	 */
	public Product findById(int pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * ��ѯ���������¶�Ӧ����Ʒ����
	 * @param csid
	 * @return
	 */
	public int findTotalByCsid(Integer csid) {
		List<Long> list = this.getHibernateTemplate().find("select count(*) from Product p,CategorySecond cs where p.categorySecond=cs and cs.csid=?", csid);
		int total = list.get(0).intValue();
		return total;
	}

	/**
	 * ��ѯ���������¶�Ӧ����Ʒ
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
	 * ��̨��ѯ������Ʒ��¼��
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
	 * ��ѯ������Ʒ����ҳ
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
	 * ��̨�����Ʒ
	 * @param product
	 */
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/**
	 * ��ѯָ����Ʒ
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
	 * ɾ����Ʒ
	 * @param pro
	 */
	public void delete(Product pro) {
		this.getHibernateTemplate().delete(pro);
	}

	/**
	 * �޸���Ʒ
	 * @param product
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
	
	
	/**
	 * ��ѯָ����Ʒ���
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
	 * ���³ߴ���ָ����
	 * @param sizeColumn
	 * @param size
	 */
	public void updateSize(String sizeColumn,Size size) {
		
		this.getHibernateTemplate().update(sizeColumn, size);
	}
	
	/**
	 * ��ѯָ����Ʒ��Ŷ�Ӧ������id
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
	 * ͨ��id��ѯָ����Ʒ�ĳߴ���
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
	 * ������
	 * @param size
	 */
	public void updateSize(Size size) {
		this.getHibernateTemplate().update(size);
	}
	
	
	/**
	 * ͨ����Ʒid��ѯ������һ������
	 * @param pid
	 * @return
	 */
	public Integer findCidByPid(Integer pid) {
		
		String hql = "SELECT cs.category.cid FROM Product p,CategorySecond cs WHERE p.categorySecond.csid = cs.csid AND p.pid = ?";
		
		//����ѯ
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
