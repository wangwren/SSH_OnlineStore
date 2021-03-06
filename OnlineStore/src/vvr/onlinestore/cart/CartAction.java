package vvr.onlinestore.cart;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.onlinestore.product.Product;
import vvr.onlinestore.product.ProductService;
/**
 * 购物车相关
 * @author wwr
 *
 */
public class CartAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 835294499040725173L;
	
	private Integer pid;
	private Integer count;
	
	//尺码
	private String size = null;
	
	private ProductService productService;
	private Map<String,Object> session;
	
	
	public void setSize(String size) {
		this.size = size;
	}

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
	 * 获得购物车
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
	 * 添加至购物车
	 * @return
	 */
	public String addCart() {
		
		//购物项
		CartItem cartItem = new CartItem();
		if(size != null) {
			size = size.toUpperCase();
			cartItem.setSize(size);
		}
		cartItem.setCount(count);
		
		Integer cid = productService.findCidByPid(pid);
		//如果cid查询到是1，就存在session中，方便前台显示尺寸，否则就不存
		if(cid == 1) {
			
			session.put("cid", cid);
			
			//重复代码，可以重构
			findPAndAddCart(cartItem);
			
			return "addCartSuccess";
		}else {
			
			findPAndAddCart(cartItem);
			
			return "addCartSuccess";
		}
	}

	
	
	/**
	 * 清空购物车
	 * @return
	 */
	public String clearCart() {
		
		Cart cart = getCart(session);
		cart.clear();
		
		return "clearCartSuccess";
	}
	
	/**
	 * 删除某一个购物项
	 * @return
	 */
	public String del() {
		
		Cart cart = getCart(session);
		cart.del(pid);
		
		return "delSuccess";
	}
	
	/**
	 * 我的购物车
	 * @return
	 */
	public String myCart() {
		
		return "myCartSuccess";
	}
	
	
	/**
	 * @param cartItem 购物车
	 * 查询指定编号的商品，添加至购物车
	 */
	private void findPAndAddCart(CartItem cartItem) {
		Product product = productService.findById(pid);
		cartItem.setProduct(product);
		
		//添加至购物车
		Cart cart = getCart(session);
		cart.addCart(cartItem);
	}

}
