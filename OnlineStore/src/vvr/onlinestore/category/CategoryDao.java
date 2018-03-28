package vvr.onlinestore.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport{

	/**
	 * 查询所有一级分类
	 * @return
	 */
	public List<Category> findAll() {
		return this.getHibernateTemplate().find("from Category");
	}

	/**
	 * 后台添加一级分类
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	/**
	 * 后台删除一级分类
	 * @param category
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * 后台查询指定一级分类
	 * @param category
	 * @return
	 */
	public Category findByCid(Integer cid) {
 		
		Category category = this.getHibernateTemplate().get(Category.class, cid);
		return category;
	}

	/**
	 * 后台修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}


	
}
