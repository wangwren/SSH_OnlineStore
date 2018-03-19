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
	
}
