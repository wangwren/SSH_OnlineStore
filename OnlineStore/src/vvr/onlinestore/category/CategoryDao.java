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


	
}
