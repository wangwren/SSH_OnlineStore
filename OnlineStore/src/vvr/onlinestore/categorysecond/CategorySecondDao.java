package vvr.onlinestore.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import vvr.onlinestore.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	/**
	 * ��ѯ���ж���������
	 * @return
	 */
	public Integer findTotal() {
		
		List<Long> list = this.getHibernateTemplate().find("select count(*) from CategorySecond");
		if(list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	/**
	 * ��̨��ҳ��ѯ��������
	 * @param begin
	 * @param page
	 * @return
	 */
	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		
		List<CategorySecond> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<CategorySecond>("from CategorySecond", null, begin, limit));
		if(list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * ��Ӷ�������
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * ɾ����������
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * ��ѯָ���������������һ������
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		CategorySecond categorySecond = this.getHibernateTemplate().get(CategorySecond.class, csid);
		return categorySecond;
	}

	/**
	 * �޸Ķ�������
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * ��̨�����Ʒ����
	 * @return
	 */
	public List<CategorySecond> findAll() {
		List<CategorySecond> list = this.getHibernateTemplate().find("from CategorySecond");
		if(list.size() > 0) {
			return list;
		}
		return null;
	}

}
