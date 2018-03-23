package vvr.onlinestore.order;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.onlinestore.cart.Cart;
import vvr.onlinestore.cart.CartItem;
import vvr.onlinestore.user.User;

public class OrdersAction extends ActionSupport implements SessionAware,RequestAware{

	private static final long serialVersionUID = 5424956490917354714L;

	private Map<String,Object> session;
	private Map<String,Object> request;
	private Orders order;
	
	private OrdersService ordersService;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	/**
	 * 保存订单以及订单项
	 * @return
	 */
	public String saveOrder() {
		
		//获取购物车
		Cart cart = (Cart) session.get("cart");
		if(cart == null) {
			request.put("message", "确认订单失败，您的购物车为空！！！");
			return "saveOrderError";
		}
		
		//获取用户
		User user = (User) session.get("user");
		if(user == null) {
			request.put("message", "请先登录！！！");
			return "saveOrderError";
		}
		
		//封装订单
		order = new Orders();
		order.setOrdertime(new Date());
		order.setState(0); 		//0代表订单未支付     1代表支付未发货        2代表发货未确认收货    3代表确认收货
		order.setTotal(cart.getTotal());
		order.setUser(user);
		
		//封装订单项,在映射配置文件中设置级联操作
		for(CartItem item:cart.getCartItems()) {
			OrderItem oitem = new OrderItem();
			oitem.setCount(item.getCount());
			oitem.setSubtotal(item.getSubtotal());
			oitem.setProduct(item.getProduct());
			oitem.setOrders(order);
			
			//将订单项设置到订单中
			order.getOrderItems().add(oitem);
		}
		
		//保存订单；
		Integer oid = ordersService.saveOrder(order);
		//保存成功后，再将oid设置到order对象中，方便显示，不用再去查数据库
		order.setOid(oid);
		//清空购物车
		cart.clear();
		
		return "saveOrderSuccess";
	}

}
