package vvr.onlinestore.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport{

	/**
	 * ��ѯ����һ������
	 * @return
	 */
	public List<Category> findAll() {
		return this.getHibernateTemplate().find("from Category");
	}

	/**
	 * ��̨���һ������
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	/**
	 * ��̨ɾ��һ������
	 * @param category
	 */
	public void delete(Category category) {
		
		//�����Ȳ�ѯ��һ�����������ɼ���������ʹ�����е����ݶ��в��У�����id������ɼ���
		category = this.getHibernateTemplate().get(Category.class, category.getCid());
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * ��̨��ѯָ��һ������
	 * @param category
	 * @return
	 */
	public Category findByCid(Integer cid) {
 		
		Category category = this.getHibernateTemplate().get(Category.class, cid);
		return category;
	}

	/**
	 * ��̨�޸�һ������
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}


	
}
