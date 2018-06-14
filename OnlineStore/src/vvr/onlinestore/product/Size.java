package vvr.onlinestore.product;
/**
 * 尺码表库存
 * @author wwr
 *
 */
public class Size {

	private Integer id;
	
	//以下为对应的尺码，存的是数量
	private Integer xlSize;
	private Integer xxlSize;
	private Integer sSize;
	private Integer mSize;
	
	//商品
	private Product product;
	
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getXlSize() {
		return xlSize;
	}
	public void setXlSize(Integer xlSize) {
		this.xlSize = xlSize;
	}
	public Integer getXxlSize() {
		return xxlSize;
	}
	public void setXxlSize(Integer xxlSize) {
		this.xxlSize = xxlSize;
	}
	public Integer getsSize() {
		return sSize;
	}
	public void setsSize(Integer sSize) {
		this.sSize = sSize;
	}
	public Integer getmSize() {
		return mSize;
	}
	public void setmSize(Integer mSize) {
		this.mSize = mSize;
	}
	
	
}
