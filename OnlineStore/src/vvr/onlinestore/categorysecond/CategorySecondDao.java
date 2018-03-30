package vvr.onlinestore.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import vvr.onlinestore.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	/**
	 * 查询所有二级分类数
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
	 * 后台分页查询二级分类
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
	 * 添加二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * 删除二级分类
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * 查询指定二级分类和所有一级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		CategorySecond categorySecond = this.getHibernateTemplate().get(CategorySecond.class, csid);
		return categorySecond;
	}

	/**
	 * 修改二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * 后台添加商品界面
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
