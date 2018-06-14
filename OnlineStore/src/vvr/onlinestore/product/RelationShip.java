package vvr.onlinestore.product;

/**
 * ¹ØÏµ
 * @author wwr
 *
 */
public class RelationShip {

	private Integer id;
	
	private Size size;
	private Product product;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
