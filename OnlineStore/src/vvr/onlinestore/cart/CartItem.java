package vvr.onlinestore.cart;

import vvr.onlinestore.product.Product;

/**
 * ������
 * @author wwr
 *
 */
public class CartItem {

	//��Ʒ
	private Product product;
	
	//����
	private Integer count = 0;
	
	//С��
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
	 * С�Ʋ���Ҫ���ⲿ����ֵ�����Բ���Ҫ����set����
	 * @return
	 */
	public Double getSubtotal() {
		return count * product.getShop_price();
	}

}
