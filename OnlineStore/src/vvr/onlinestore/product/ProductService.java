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
	 * 查询热门商品
	 * @return
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}

	/**
	 * 查询最新商品
	 * @return
	 */
	public List<Product> findNew() {
		return productDao.findNew();
	}

	/**
	 * 分页查询一级分类对应的商品
	 * @param cid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findProductToCid(Integer cid, Integer page) {
		
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);	//页数
		int limit = 12;		//每页个数
		pageBean.setLimit(limit);
		
		//查询总记录数
		int total = productDao.findTotal(cid);
		pageBean.setTotal(total);
		
		//总页数
		int totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = (total / limit) + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//查询一级分类的商品
		List<Product> list = productDao.findProductToCid(cid,page,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 查询某个商品的详细信息
	 * @return
	 */
	public Product findById(int pid) {
		return productDao.findById(pid);
	}

	/**
	 * 查询二级分类对应的商品
	 * @param csid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);	//页数
		int limit = 8;		//每页个数
		pageBean.setLimit(limit);
		
		//查询总记录数
		int total = productDao.findTotalByCsid(csid);
		pageBean.setTotal(total);
		
		//总页数
		int totalPage = 0;
		if(total % limit == 0) {
			totalPage = total / limit;
		}else {
			totalPage = (total / limit) + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		//查询一级分类的商品
		List<Product> list = productDao.findByCsid(csid,page,limit);
		pageBean.setList(list);
		
		return pageBean;
	}
	
}
