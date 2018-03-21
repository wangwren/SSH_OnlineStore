package vvr.onlinestore.cart;

import vvr.onlinestore.product.Product;

/**
 * 购物项
 * @author wwr
 *
 */
public class CartItem {

	//商品
	private Product product;
	
	//数量
	private Integer count = 0;
	
	//小计
	private Double subtotal = 0d;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 小计不需要从外部设置值，所以不需要设置set方法
	 * @return
	 */
	public Double getSubtotal() {
		return count * product.getShop_price();
	}

}
