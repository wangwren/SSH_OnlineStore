package vvr.onlinestore.product;
/**
 * �������
 * @author wwr
 *
 */
public class Size {

	private Integer id;
	
	//����Ϊ��Ӧ�ĳ��룬���������
	private Integer xlSize;
	private Integer xxlSize;
	private Integer ssSize;
	private Integer mmSize;
	
	//��Ʒ
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
	public Integer getSsSize() {
		return ssSize;
	}
	public void setSsSize(Integer ssSize) {
		this.ssSize = ssSize;
	}
	public Integer getMmSize() {
		return mmSize;
	}
	public void setMmSize(Integer mmSize) {
		this.mmSize = mmSize;
	}
	
	
}
