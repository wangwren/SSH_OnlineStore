package vvr.onlinestore.categorysecond;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vvr.onlinestore.utils.PageBean;
@Transactional
public class CategorySecondService {
	
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}


	/**
	 * ��̨��ѯ�������࣬����ҳ
	 * @param page
	 * @return
	 */
	public PageBean<CategorySecond> findByPage(Integer page) {
		
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		Integer limit = 10;
		pageBean.setLimit(limit);
		Integer total = 0;
		total = categorySecondDao.findTotal();
		//�ܼ�¼��
		pageBean.setTotal(total);
		Integer totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = total / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		Integer begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * ��Ӷ�������
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}


	/**
	 * ɾ����������
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}


	/**
	 * ��ѯָ���������������һ������
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}


	/**
	 * �޸Ķ�������
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

}
