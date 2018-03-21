package vvr.onlinestore.cart;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.onlinestore.product.Product;
import vvr.onlinestore.product.ProductService;
/**
 * ���ﳵ���
 * @author wwr
 *
 */
public class CartAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 835294499040725173L;
	
	private Integer pid;
	private Integer count;
	
	private ProductService productService;
	private Map<String,Object> session;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * ��ù��ﳵ
	 * @return
	 */
	public Cart getCart(Map<String,Object> session) {
		
		Cart cart = (Cart) session.get("cart");
		if(cart == null) {
			cart = new Cart();
			session.put("cart", cart);
		}
		return cart;
	}

	/**
	 * ��������ﳵ
	 * @return
	 */
	public String addCart() {
		
		//������
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		
		Product product = productService.findById(pid);
		cartItem.setProduct(product);
		
		//��������ﳵ
		Cart cart = getCart(session);
		cart.addCart(cartItem);
		
		
		return "addCartSuccess";
	}
	
	/**
	 * ��չ��ﳵ
	 * @return
	 */
	public String clearCart() {
		
		Cart cart = getCart(session);
		cart.clear();
		
		return "clearCartSuccess";
	}
	
	/**
	 * ɾ��ĳһ��������
	 * @return
	 */
	public String del() {
		
		Cart cart = getCart(session);
		cart.del(pid);
		
		return "delSuccess";
	}
	
	/**
	 * �ҵĹ��ﳵ
	 * @return
	 */
	public String myCart() {
		
		return "myCartSuccess";
	}

}
