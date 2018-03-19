package vvr.onlinestore.product;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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

}
