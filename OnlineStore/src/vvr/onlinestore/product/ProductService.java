package vvr.onlinestore.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vvr.onlinestore.utils.PageBean;

@Transactional
public class ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	public List<Product> findNew() {
		return productDao.findNew();
	}

	/**
	 * ��ҳ��ѯһ�������Ӧ����Ʒ
	 * @param cid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findProductToCid(Integer cid, Integer page) {
		
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);	//ҳ��
		int limit = 12;		//ÿҳ����
		pageBean.setLimit(limit);
		
		//��ѯ�ܼ�¼��
		int total = productDao.findTotal(cid);
		pageBean.setTotal(total);
		
		//��ҳ��
		int totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = (total / limit) + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//��ѯһ���������Ʒ
		List<Product> list = productDao.findProductToCid(cid,page,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * ��ѯĳ����Ʒ����ϸ��Ϣ
	 * @return
	 */
	public Product findById(int pid) {
		return productDao.findById(pid);
	}

	/**
	 * ��ѯ���������Ӧ����Ʒ
	 * @param csid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);	//ҳ��
		int limit = 8;		//ÿҳ����
		pageBean.setLimit(limit);
		
		//��ѯ�ܼ�¼��
		int total = productDao.findTotalByCsid(csid);
		pageBean.setTotal(total);
		
		//��ҳ��
		int totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = (total / limit) + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//��ѯһ���������Ʒ
		List<Product> list = productDao.findByCsid(csid,page,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * ��̨��ѯ������Ʒ����ҳ
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		Integer limit = 10;
		pageBean.setLimit(limit);
		Integer total = productDao.findTotal();
		pageBean.setTotal(total);
		Integer totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = total / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		Integer begin = (page - 1) * limit;
		List<Product> list = productDao.findAllProduct(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * ��̨�����Ʒ
	 * @param product
	 */
	public void save(Product product) {
		productDao.save(product);
	}

	/**
	 * ��ѯָ����Ʒ
	 * @param pid
	 * @return
	 */
	public Product findByPid(int pid) {
		return productDao.findByPid(pid);
	}

	/**
	 * ɾ����Ʒ
	 * @param pro
	 */
	public void delete(Product pro) {
		productDao.delete(pro);
	}

	/**
	 * �޸���Ʒ
	 * @param product
	 */
	public void update(Product product) {
		productDao.update(product);
	}
	
	
	public Integer findSize(Integer id,String size) {
		return productDao.findSize(id, size);
	}
	
	
	public void updateSize(String sizeColumn,Size size) {
		
		productDao.updateSize(sizeColumn, size);
	}
	
	public Integer findSizeId(Integer id) {
		return productDao.findSizeId(id);
	}
	
	public Size findSizeByPid(Integer pid) {
		return productDao.findSizeByPid(pid);
	}
	
	public void updateSize(Size size) {
		productDao.updateSize(size);
	}
	
	public Integer findCidByPid(Integer pid) {
		
		return productDao.findCidByPid(pid);
	}
	
	public void updateOrSaveSize(Size size) {
		productDao.updateOrSaveSize(size);
	}
}
