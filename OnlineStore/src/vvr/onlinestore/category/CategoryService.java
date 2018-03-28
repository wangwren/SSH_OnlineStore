package vvr.onlinestore.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategoryService {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 查询出所有一级分类
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * 后台添加一级分类
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}

	/**
	 * 后台删除一级分类
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/**
	 * 后台查询指定一级分类
	 * @param category
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	/**
	 * 后台修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
