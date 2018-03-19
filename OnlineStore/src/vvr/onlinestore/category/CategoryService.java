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
	 * ��ѯ������һ������
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
}
