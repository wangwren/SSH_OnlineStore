package vvr.onlinestore.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

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
	
}
